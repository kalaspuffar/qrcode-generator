package org.ea;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CharacterEncoding {
    NUMERIC("0001", new int[] {10, 12, 14}, "0123456789"),
    ALPLANUMERIC("0010", new int[] {9, 11, 13}, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:"),
    BYTE("0100", new int[] {8, 16, 16}, null),
    //KANJI("1000", new int[] {8, 10, 12}, null)
    ;

    private static final int[] BREAK_POINTS = new int[] {0, 9, 26};

    private final String bitID;
    private final int[] bitLen;
    private final String encodingString;

    CharacterEncoding(String bitID, int[] bitLen, String encodingString) {
        this.bitID = bitID;
        this.bitLen = bitLen;
        this.encodingString = encodingString;
    }

    public String getBitID() {
        return bitID;
    }

    public int getBitLen(int level) {
        if(level > BREAK_POINTS[2]) {
            return bitLen[2];
        }
        if(level > BREAK_POINTS[1]) {
            return bitLen[1];
        }
        return bitLen[0];
    }

    public static int getCurrentBreakPoint(int level) {
        if(level < BREAK_POINTS[1]) {
            return BREAK_POINTS[1];
        }
        if(level < BREAK_POINTS[2]) {
            return BREAK_POINTS[2];
        }
        return 40;
    }

    protected String writeIntToBits(int val, int len) {
        StringBuilder bits = new StringBuilder();
        String s = Integer.toBinaryString(val);
        for(int i = s.length(); i < len; i++) {
            bits.append("0");
        }
        bits.append(s);
        return bits.toString();
    }

    protected String writeByte(byte b) {
        return writeIntToBits(b & 0xFF, 8);
    }

    public String encodeMessage(String message) {
        switch (this) {
            case NUMERIC:
                return encodeNumericString(message);
            case ALPLANUMERIC:
                return encodeAlphaNumericString(message);
            case BYTE:
                StringBuilder sb = new StringBuilder();
                for(byte b : message.getBytes(StandardCharsets.UTF_8)) {
                    sb.append(getEncodedByte(b));
                }
                return sb.toString();
        }
        return "";
    }


    private String encodeAlphaNumericString(String message) {
        StringBuilder sb = new StringBuilder();
        Matcher m = Pattern.compile("[0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+\\-./:]{1,2}").matcher(message);
        while (m.find()) {
            int len = m.end() - m.start();
            if(len == 2) {
                char val1 = message.charAt(m.start());
                char val2 = message.charAt(m.start() + 1);
                int val1idx = encodingString.indexOf(val1);
                int val2idx = encodingString.indexOf(val2);

                sb.append(writeIntToBits(val1idx * 45 + val2idx, 11));
            } else {
                char val1 = message.charAt(m.start());
                int val1idx = encodingString.indexOf(val1);
                sb.append(writeIntToBits(val1idx, 6));
            }
        }
        return sb.toString();
    }

    private String encodeNumericString(String message) {
        StringBuilder sb = new StringBuilder();
        Matcher m = Pattern.compile("[\\d]{1,3}").matcher(message);
        while (m.find()) {
            int len = m.end() - m.start();
            int val = Integer.parseInt(message.substring(m.start(), m.end()));
            if(len == 3) {
                sb.append(writeIntToBits(val, 10));
            } else if(len == 2) {
                sb.append(writeIntToBits(val, 7));
            } else {
                sb.append(writeIntToBits(val, 4));
            }
        }
        return sb.toString();
    }

    public String getEncodedByte(byte b) {
        if (encodingString == null) return writeByte(b);

        byte inc = 0;
        for (byte encB : encodingString.getBytes(StandardCharsets.UTF_8)) {
            if (encB == b) return writeIntToBits(inc, 8);
            inc++;
        }
        return "";
    }

    public String writeEncAndLen(int length, Version version) {
        StringBuilder bits = new StringBuilder();
        bits.append(this.getBitID());
        bits.append(writeIntToBits(length, this.getBitLen(version.getVersion())));
        return bits.toString();
    }
}
