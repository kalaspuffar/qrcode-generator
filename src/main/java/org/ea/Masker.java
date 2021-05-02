package org.ea;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Masker {
    private final Map<Integer, int[]> masks = new HashMap<>();

    private int[] mask(int[] qrCode, Version version, int mask) {
        int[] newQRCode = Arrays.copyOf(qrCode, qrCode.length);

        int size = version.getSize();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (maskTrue(mask, row, column)) {
                    int place = row * size + column;
                    if (newQRCode[place] == Constants.DATA_BIT_0) {
                        newQRCode[place] = Constants.DATA_BIT_1;
                    } else if (newQRCode[place] == Constants.DATA_BIT_1) {
                        newQRCode[place] = Constants.DATA_BIT_0;
                    }
                }
            }
        }
        return newQRCode;
    }

    public static void drawFormatString(int[] qrCode, Version version, int[] formatString) {
        int size = version.getSize();

        qrCode[8 * size + 0] = formatString[0];
        qrCode[8 * size + 1] = formatString[1];
        qrCode[8 * size + 2] = formatString[2];
        qrCode[8 * size + 3] = formatString[3];
        qrCode[8 * size + 4] = formatString[4];
        qrCode[8 * size + 5] = formatString[5];

        qrCode[8 * size + 7] = formatString[6];
        qrCode[8 * size + 8] = formatString[7];
        qrCode[7 * size + 8] = formatString[8];

        qrCode[5 * size + 8] = formatString[9];
        qrCode[4 * size + 8] = formatString[10];
        qrCode[3 * size + 8] = formatString[11];
        qrCode[2 * size + 8] = formatString[12];
        qrCode[1 * size + 8] = formatString[13];
        qrCode[0 * size + 8] = formatString[14];


        qrCode[(size - 1) * size + 8] = formatString[0];
        qrCode[(size - 2) * size + 8] = formatString[1];
        qrCode[(size - 3) * size + 8] = formatString[2];
        qrCode[(size - 4) * size + 8] = formatString[3];
        qrCode[(size - 5) * size + 8] = formatString[4];
        qrCode[(size - 6) * size + 8] = formatString[5];
        qrCode[(size - 7) * size + 8] = formatString[6];

        qrCode[8 * size + size - 8] = formatString[7];
        qrCode[8 * size + size - 7] = formatString[8];
        qrCode[8 * size + size - 6] = formatString[9];
        qrCode[8 * size + size - 5] = formatString[10];
        qrCode[8 * size + size - 4] = formatString[11];
        qrCode[8 * size + size - 3] = formatString[12];
        qrCode[8 * size + size - 2] = formatString[13];
        qrCode[8 * size + size - 1] = formatString[14];
    }

    private boolean maskTrue(int mask, int row, int column) {
        switch (mask) {
            case 0:
                return (row + column) % 2 == 0;
            case 1:
                return row % 2 == 0;
            case 2:
                return column % 3 == 0;
            case 3:
                return (row + column) % 3 == 0;
            case 4:
                return (Math.floorDiv(row, 2) + Math.floorDiv(column, 3)) % 2 == 0;
            case 5:
                return ((row * column) % 2) + ((row * column) % 3) == 0;
            case 6:
                return ( ((row * column) % 2) + ((row * column) % 3) ) % 2 == 0;
            case 7:
                return (((row + column) % 2) + ((row * column) % 3)) % 2 == 0;
        }
        return false;
    }

    public int calculateMask(int[] qrCode, Version version, Quality quality) {
        for(int i = 0; i < 8; i++) {
            int[] newQR = mask(qrCode, version, i);
            drawFormatString(newQR, version, getMaskSign(quality, i));
            finalizeQR(newQR);
            masks.put(i, newQR);
        }

        int smallestPenalty = Integer.MAX_VALUE;
        int bestMask = -1;
        for(int i = 0; i < 8; i++) {
            int amount = calculatePenalty(masks.get(i), version);

            System.out.println(i + " = " + amount);

            if(amount < smallestPenalty) {
                bestMask = i;
                smallestPenalty = amount;
            }
        }

        return bestMask;
    }

    private void finalizeQR(int[] newQR) {
        for(int j = 0; j < newQR.length; j++) {
            if (
                newQR[j] == Constants.FORMAT_BIT_0 ||
                newQR[j] == Constants.VERSION_BIT_0 ||
                newQR[j] == Constants.DATA_BIT_0
            ) {
                newQR[j] = Constants.FINAL_BIT_0;
            }
            if (
                newQR[j] == Constants.FORMAT_BIT_1 ||
                newQR[j] == Constants.VERSION_BIT_1 ||
                newQR[j] == Constants.DATA_BIT_1
            ) {
                newQR[j] = Constants.FINAL_BIT_1;
            }
        }
    }

    private int calculatePenalty(int[] qrCode, Version version) {
        int size = version.getSize();
        int penalty = 0;
        penalty += calculateRule1(qrCode, size);
        penalty += calculateRule2(qrCode, size);
        penalty += calculateRule3(qrCode, size);
        penalty += calculateRule4(qrCode);

        return penalty;
    }

    private int calculateRule1(int[] qrCode, int size) {
        int penalty = 0;
        for (int y = 0; y < size - 1; y++) {
            int lastColor = -1;
            int count = 0;
            for (int x = 0; x < size - 1; x++) {
                if (qrCode[y * size + x] == lastColor) {
                    count++;
                } else {
                    if(count > 5) {
                        penalty += count - 3;
                    }
                    lastColor = qrCode[y * size + x];
                    count = 0;
                }
            }
            if(count > 5) {
                penalty += count - 3;
            }
        }

        for (int x = 0; x < size - 1; x++) {
            int lastColor = -1;
            int count = 0;
            for (int y = 0; y < size - 1; y++) {
                if (qrCode[y * size + x] == lastColor) {
                    count++;
                } else {
                    if(count > 5) {
                        penalty += count - 3;
                    }
                    lastColor = qrCode[y * size + x];
                    count = 0;
                }
            }
            if(count > 5) {
                penalty += count - 3;
            }
        }

        return penalty;
    }

    private int calculateRule2(int[] qrCode, int size) {
        int penalty = 0;
        for (int y = 0; y < size - 1; y++) {
            for (int x = 0; x < size - 1; x++) {
                int color1 = qrCode[y * size + x];
                int color2 = qrCode[(y + 1) * size + x];
                int color3 = qrCode[y * size + x + 1];
                int color4 = qrCode[(y + 1) * size + x + 1];
                if (
                    color1 == color2 &&
                    color1 == color3 &&
                    color1 == color4
                ) {
                    penalty += 3;
                }
            }
        }
        return penalty;
    }

    private int calculateRule3(int[] qrCode, int size) {
        int[] pattern1 = new int[] {
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_0,
        };
        int[] pattern2 = new int[] {
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_1,
            Constants.FINAL_BIT_0,
            Constants.FINAL_BIT_1,
        };

        int penalty = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size - pattern1.length + 1; x++) {
                int i = 0;
                for (; i < pattern1.length; i++) {
                    if(qrCode[y * size + x + i] != pattern1[i]) {
                        break;
                    }
                }
                if (i == pattern1.length) {
                    penalty += 40;
                }

                i = 0;
                for (; i < pattern2.length; i++) {
                    if(qrCode[y * size + x + i] != pattern2[i]) {
                        break;
                    }
                }
                if (i == pattern2.length) {
                    penalty += 40;
                }
            }
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size - pattern1.length + 1; y++) {
                int i = 0;
                for (; i < pattern1.length; i++) {
                    if(qrCode[(y + i) * size + x] != pattern1[i]) {
                        break;
                    }
                }
                if (i == pattern1.length) {
                    penalty += 40;
                }

                i = 0;
                for (; i < pattern2.length; i++) {
                    if(qrCode[(y + i) * size + x] != pattern2[i]) {
                        break;
                    }
                }
                if (i == pattern2.length) {
                    penalty += 40;
                }
            }
        }

        return penalty;
    }

    private int calculateRule4(int[] qrCode) {
        int dark = 0;
        int light = 0;
        for (int val : qrCode) {
            if(val == Constants.FINAL_BIT_0) {
                light++;
            }
            if(val == Constants.FINAL_BIT_1) {
                dark++;
            }
        }

        double percent =
            ((double)Math.min(light, dark) / (double)Math.max(light, dark)) * 100d;

        double upper = 5 * (Math.ceil(Math.abs(percent/5)));
        double lower = 5 * (Math.floor(Math.abs(percent/5)));

        double upperCalc = Math.abs(upper - 50d) / 5d;
        double lowerCalc = Math.abs(lower - 50d) / 5d;

        double difference = Math.min(upperCalc, lowerCalc);

        return (int)difference * 10;
    }

    private int[] getMaskSign(Quality quality, int maskVersion) {
        String maskString = null;
        if (quality == Quality.LEVEL_L) {
            maskString = Constants.LOW_ECC_MASKS[maskVersion];
        }
        if (quality == Quality.LEVEL_M) {
            maskString = Constants.MID_ECC_MASKS[maskVersion];
        }
        if (quality == Quality.LEVEL_Q) {
            maskString = Constants.QUALITY_ECC_MASKS[maskVersion];
        }
        if (quality == Quality.LEVEL_H) {
            maskString = Constants.HIGH_ECC_MASKS[maskVersion];
        }
        if(maskString == null) throw new IllegalArgumentException("No mask string defined");

        int[] maskBytes = new int[15];
        int maskCount = 0;
        for (String s : maskString.split("")) {
            maskBytes[maskCount] = s.equals("1") ? Constants.FORMAT_BIT_1 : Constants.FORMAT_BIT_0;
            maskCount++;
        }
        return maskBytes;
    }

    public int[] getMask(int optimialMask) {
        return masks.get(optimialMask);
    }
}
