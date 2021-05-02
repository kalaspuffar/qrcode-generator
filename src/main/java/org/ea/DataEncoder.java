package org.ea;

import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DataEncoder {
    private StringBuilder bits = new StringBuilder();
    private Version version;

    public Version writeData(Quality quality, CharacterEncoding enc, String message, Version version) {
        bits = new StringBuilder();

        bits.append(enc.writeEncAndLen(message.length(), version));
        bits.append(enc.encodeMessage(message));

        for (Version v : Version.values()) {
            if (v.getBits(quality) >= bits.length()) {
                version = v;
                break;
            }
        }

        return version;
    }

    public void encode(Quality quality, CharacterEncoding characterEncoding, String message) {

        int breakPoint = CharacterEncoding.getCurrentBreakPoint(1);
        version = writeData(quality, characterEncoding, message, Version.VERSION_1);
        if (version.getVersion() > breakPoint) {
            version = writeData(quality, characterEncoding, message, version);
        }


        int endOfExtraBits = Math.min(version.getBits(quality), bits.length() + 4);
        for (int i = bits.length(); i < endOfExtraBits; i++) {
            bits.append("0");
        }

        for(int i = 0; i < bits.length() % 8; i++) {
            bits.append("0");
        }

        while(true) {
            if(bits.length() >= version.getBits(quality)) break;
            bits.append(CharacterEncoding.BYTE.getEncodedByte((byte)236));
            if(bits.length() >= version.getBits(quality)) break;
            bits.append(CharacterEncoding.BYTE.getEncodedByte((byte)17));
        }

        byte[][] groups = createGroups(version, quality, strToBytes(bits.toString()));

        byte[][] ecc = new byte[groups.length][];
        int longestGroups = 0;
        int eccCodeWords = version.getEccCodewordsPerBlock(quality);
        for (int i = 0; i < groups.length; i++) {
            longestGroups = Math.max(longestGroups, groups[i].length);
            ecc[i] = generateECBytes(
                    groups[i],
                    eccCodeWords
            );
        }

        bits = new StringBuilder();

        for (int i = 0; i < longestGroups; i++) {
            for (int j = 0; j < groups.length; j++) {
                if (groups[j].length > i) {
                    bits.append(CharacterEncoding.BYTE.getEncodedByte(groups[j][i]));
                }
            }
        }

        for (int i = 0; i < eccCodeWords; i++) {
            for (int j = 0; j < ecc.length; j++) {
                bits.append(CharacterEncoding.BYTE.getEncodedByte(ecc[j][i]));
            }
        }

        bits.append("00000000");
    }

    protected byte[] strToBytes(String str) {
        byte[] bitsBytes = new byte[str.length() / 8];
        int i = 0;
        for (String s : str.split("(?<=\\G.{8})")) {
            byte b = 0;
            b |= (s.charAt(0) == '1' ? 1 : 0) << 7;
            b |= (s.charAt(1) == '1' ? 1 : 0) << 6;
            b |= (s.charAt(2) == '1' ? 1 : 0) << 5;
            b |= (s.charAt(3) == '1' ? 1 : 0) << 4;
            b |= (s.charAt(4) == '1' ? 1 : 0) << 3;
            b |= (s.charAt(5) == '1' ? 1 : 0) << 2;
            b |= (s.charAt(6) == '1' ? 1 : 0) << 1;
            b |= (s.charAt(7) == '1' ? 1 : 0) << 0;
            bitsBytes[i++] = b;
        }
        return bitsBytes;
    }

    private byte[][] createGroups(Version version, Quality quality, byte[] bits) {
        int blocksInGroup1 = version.getBlocksInGroup1(quality);
        int blocksInGroup2 = version.getBlocksInGroup2(quality);
        int wordsInGroup1 = version.getCodewordsInGroup1(quality);
        int wordsInGroup2 = version.getCodewordsInGroup2(quality);
        byte[][] ret = new byte[blocksInGroup1 + blocksInGroup2][];

        int counter = 0;
        for (int i = 0; i < blocksInGroup1; i++) {
            ret[i] = Arrays.copyOfRange(bits, counter, counter + wordsInGroup1);
            counter += wordsInGroup1;
        }
        for (int i = 0; i < blocksInGroup2; i++) {
            ret[blocksInGroup1 + i] = Arrays.copyOfRange(bits, counter, counter + wordsInGroup2);
            counter += wordsInGroup2;
        }
        return ret;
    }

    static byte[] generateECBytes(byte[] dataBytes, int numEcBytesInBlock) {
        int numDataBytes = dataBytes.length;
        int[] toEncode = new int[numDataBytes + numEcBytesInBlock];
        for (int i = 0; i < numDataBytes; i++) {
            toEncode[i] = dataBytes[i] & 0xFF;
        }

        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(toEncode, numEcBytesInBlock);

        byte[] ecBytes = new byte[numEcBytesInBlock];
        for (int i = 0; i < numEcBytesInBlock; i++) {
            ecBytes[i] = (byte) toEncode[numDataBytes + i];
        }
        return ecBytes;
    }

    public Version getVersion() {
        return version;
    }

    public int[] getVersionBytes() {
        int[] val = new int[version.getIdentity().length()];
        int i = 0;
        for(String s : version.getIdentity().split("")) {
            val[i++] = s.equals("1") ? Constants.VERSION_BIT_1 : Constants.VERSION_BIT_0;
        }
        return val;
    }

    public int[] getMessage() {
        int[] val = new int[bits.length()];
        int i = 0;
        for(String s : bits.toString().split("")) {
            val[i] = s.equals("1") ? Constants.DATA_BIT_1 : Constants.DATA_BIT_0;
            i++;
        }
        return val;
    }
}
