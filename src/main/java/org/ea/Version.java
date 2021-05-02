package org.ea;

public enum Version {
    VERSION_1 (1, "000000000000000000", new int[] {152, 128, 104, 72}, new int[] {7, 10, 13, 17}, new int[] {1, 1, 1, 1}, new int[] {19, 16, 13, 9}, new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}),
    VERSION_2 (2, "000000000000000000", new int[] {272, 224, 176, 128}, new int[] {10, 16, 22, 28}, new int[] {1, 1, 1, 1}, new int[] {34, 28, 22, 16}, new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}),
    VERSION_3 (3, "000000000000000000", new int[] {440, 352, 272, 208}, new int[] {15, 26, 18, 22}, new int[] {1, 1, 2, 2}, new int[] {55, 44, 17, 13}, new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}),
    VERSION_4 (4, "000000000000000000", new int[] {640, 512, 384, 288}, new int[] {20, 18, 26, 16}, new int[] {1, 2, 2, 4}, new int[] {80, 32, 26, 9}, new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}),
    VERSION_5 (5, "000000000000000000", new int[] {864, 688, 496, 368}, new int[] {26, 24, 18, 22}, new int[] {1, 2, 2, 2}, new int[] {108, 43, 15, 11}, new int[] {0, 0, 2, 2}, new int[] {0, 0, 16, 12}),
    VERSION_6 (6, "000000000000000000", new int[] {1088, 864, 608, 480}, new int[] {18, 16, 24, 28}, new int[] {2, 4, 4, 4}, new int[] {68, 27, 19, 15}, new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}),
    VERSION_7 (7, "000111110010010100", new int[] {1248, 992, 704, 528}, new int[] {20, 18, 18, 26}, new int[] {2, 4, 2, 4}, new int[] {78, 31, 14, 13}, new int[] {0, 0, 4, 1}, new int[] {0, 0, 15, 14}),
    VERSION_8 (8, "001000010110111100", new int[] {1552, 1232, 880, 688}, new int[] {24, 22, 22, 26}, new int[] {2, 2, 4, 4}, new int[] {97, 38, 18, 14}, new int[] {0, 2, 2, 2}, new int[] {0, 39, 19, 15}),
    VERSION_9 (9, "001001101010011001", new int[] {1856, 1456, 1056, 800}, new int[] {30, 22, 20, 24}, new int[] {2, 3, 4, 4}, new int[] {116, 36, 16, 12}, new int[] {0, 2, 4, 4}, new int[] {0, 37, 17, 13}),
    VERSION_10(10, "001010010011010011", new int[] {2192, 1728, 1232, 976}, new int[] {18, 26, 24, 28}, new int[] {2, 4, 6, 6}, new int[] {68, 43, 19, 15}, new int[] {2, 1, 2, 2}, new int[] {69, 44, 20, 16}),
    VERSION_11(11, "001011101111110110", new int[] {2592, 2032, 1440, 1120}, new int[] {20, 30, 28, 24}, new int[] {4, 1, 4, 3}, new int[] {81, 50, 22, 12}, new int[] {0, 4, 4, 8}, new int[] {0, 51, 23, 13}),
    VERSION_12(12, "001100011101100010", new int[] {2960, 2320, 1648, 1264}, new int[] {24, 22, 26, 28}, new int[] {2, 6, 4, 7}, new int[] {92, 36, 20, 14}, new int[] {2, 2, 6, 4}, new int[] {93, 37, 21, 15}),
    VERSION_13(13, "001101100001000111", new int[] {3424, 2672, 1952, 1440}, new int[] {26, 22, 24, 22}, new int[] {4, 8, 8, 12}, new int[] {107, 37, 20, 11}, new int[] {0, 1, 4, 4}, new int[] {0, 38, 21, 12}),
    VERSION_14(14, "001110011000001101", new int[] {3688, 2920, 2088, 1576}, new int[] {30, 24, 20, 24}, new int[] {3, 4, 11, 11}, new int[] {115, 40, 16, 12}, new int[] {1, 5, 5, 5}, new int[] {116, 41, 17, 13}),
    VERSION_15(15, "001111100100101000", new int[] {4184, 3320, 2360, 1784}, new int[] {22, 24, 30, 24}, new int[] {5, 5, 5, 11}, new int[] {87, 41, 24, 12}, new int[] {1, 5, 7, 7}, new int[] {88, 42, 25, 13}),
    VERSION_16(16, "010000101101111000", new int[] {4712, 3624, 2600, 2024}, new int[] {24, 28, 24, 30}, new int[] {5, 7, 15, 3}, new int[] {98, 45, 19, 15}, new int[] {1, 3, 2, 13}, new int[] {99, 46, 20, 16}),
    VERSION_17(17, "010001010001011101", new int[] {5176, 4056, 2936, 2264}, new int[] {28, 28, 28, 28}, new int[] {1, 10, 1, 2}, new int[] {107, 46, 22, 14}, new int[] {5, 1, 15, 17}, new int[] {108, 47, 23, 15}),
    VERSION_18(18, "010010101000010111", new int[] {5768, 4504, 3176, 2504}, new int[] {30, 26, 28, 28}, new int[] {5, 9, 17, 2}, new int[] {120, 43, 22, 14}, new int[] {1, 4, 1, 19}, new int[] {121, 44, 23, 15}),
    VERSION_19(19, "010011010100110010", new int[] {6360, 5016, 3560, 2728}, new int[] {28, 26, 26, 26}, new int[] {3, 3, 17, 9}, new int[] {113, 44, 21, 13}, new int[] {4, 11, 4, 16}, new int[] {114, 45, 22, 14}),
    VERSION_20(20, "010100100110100110", new int[] {6888, 5352, 3880, 3080}, new int[] {28, 26, 30, 28}, new int[] {3, 3, 15, 15}, new int[] {107, 41, 24, 15}, new int[] {5, 13, 5, 10}, new int[] {108, 42, 25, 16}),
    VERSION_21(21, "010101011010000011", new int[] {7456, 5712, 4096, 3248}, new int[] {28, 26, 28, 30}, new int[] {4, 17, 17, 19}, new int[] {116, 42, 22, 16}, new int[] {4, 0, 6, 6}, new int[] {117, 0, 23, 17}),
    VERSION_22(22, "010110100011001001", new int[] {8048, 6256, 4544, 3536}, new int[] {28, 28, 30, 24}, new int[] {2, 17, 7, 34}, new int[] {111, 46, 24, 13}, new int[] {7, 0, 16, 0}, new int[] {112, 0, 25, 0}),
    VERSION_23(23, "010111011111101100", new int[] {8752, 6880, 4912, 3712}, new int[] {30, 28, 30, 30}, new int[] {4, 4, 11, 16}, new int[] {121, 47, 24, 15}, new int[] {5, 14, 14, 14}, new int[] {122, 48, 25, 16}),
    VERSION_24(24, "011000111011000100", new int[] {9392, 7312, 5312, 4112}, new int[] {30, 28, 30, 30}, new int[] {6, 6, 11, 30}, new int[] {117, 45, 24, 16}, new int[] {4, 14, 16, 2}, new int[] {118, 46, 25, 17}),
    VERSION_25(25, "011001000111100001", new int[] {10208, 8000, 5744, 4304}, new int[] {26, 28, 30, 30}, new int[] {8, 8, 7, 22}, new int[] {106, 47, 24, 15}, new int[] {4, 13, 22, 13}, new int[] {107, 48, 25, 16}),
    VERSION_26(26, "011010111110101011", new int[] {10960, 8496, 6032, 4768}, new int[] {28, 28, 28, 30}, new int[] {10, 19, 28, 33}, new int[] {114, 46, 22, 16}, new int[] {2, 4, 6, 4}, new int[] {115, 47, 23, 17}),
    VERSION_27(27, "011011000010001110", new int[] {11744, 9024, 6464, 5024}, new int[] {30, 28, 30, 30}, new int[] {8, 22, 8, 12}, new int[] {122, 45, 23, 15}, new int[] {4, 3, 26, 28}, new int[] {123, 46, 24, 16}),
    VERSION_28(28, "011100110000011010", new int[] {12248, 9544, 6968, 5288}, new int[] {30, 28, 30, 30}, new int[] {3, 3, 4, 11}, new int[] {117, 45, 24, 15}, new int[] {10, 23, 31, 31}, new int[] {118, 46, 25, 16}),
    VERSION_29(29, "011101001100111111", new int[] {13048, 10136, 7288, 5608}, new int[] {30, 28, 30, 30}, new int[] {7, 21, 1, 19}, new int[] {116, 45, 23, 15}, new int[] {7, 7, 37, 26}, new int[] {117, 46, 24, 16}),
    VERSION_30(30, "011110110101110101", new int[] {13880, 10984, 7880, 5960}, new int[] {30, 28, 30, 30}, new int[] {5, 19, 15, 23}, new int[] {115, 47, 24, 15}, new int[] {10, 10, 25, 25}, new int[] {116, 48, 25, 16}),
    VERSION_31(31, "011111001001010000", new int[] {14744, 11640, 8264, 6344}, new int[] {30, 28, 30, 30}, new int[] {13, 2, 42, 23}, new int[] {115, 46, 24, 15}, new int[] {3, 29, 1, 28}, new int[] {116, 47, 25, 16}),
    VERSION_32(32, "100000100111010101", new int[] {15640, 12328, 8920, 6760}, new int[] {30, 28, 30, 30}, new int[] {17, 10, 10, 19}, new int[] {115, 46, 24, 15}, new int[] {0, 23, 35, 35}, new int[] {0, 47, 25, 16}),
    VERSION_33(33, "100001011011110000", new int[] {16568, 13048, 9368, 7208}, new int[] {30, 28, 30, 30}, new int[] {17, 14, 29, 11}, new int[] {115, 46, 24, 15}, new int[] {1, 21, 19, 46}, new int[] {116, 47, 25, 16}),
    VERSION_34(34, "100010100010111010", new int[] {17528, 13800, 9848, 7688}, new int[] {30, 28, 30, 30}, new int[] {13, 14, 44, 59}, new int[] {115, 46, 24, 16}, new int[] {6, 23, 7, 1}, new int[] {116, 47, 25, 17}),
    VERSION_35(35, "100011011110011111", new int[] {18448, 14496, 10288, 7888}, new int[] {30, 28, 30, 30}, new int[] {12, 12, 39, 22}, new int[] {121, 47, 24, 15}, new int[] {7, 26, 14, 41}, new int[] {122, 48, 25, 16}),
    VERSION_36(36, "100100101100001011", new int[] {19472, 15312, 10832, 8432}, new int[] {30, 28, 30, 30}, new int[] {6, 6, 46, 2}, new int[] {121, 47, 24, 15}, new int[] {14, 34, 10, 64}, new int[] {122, 48, 25, 16}),
    VERSION_37(37, "100101010000101110", new int[] {20528, 15936, 11408, 8768}, new int[] {30, 28, 30, 30}, new int[] {17, 29, 49, 24}, new int[] {122, 46, 24, 15}, new int[] {4, 14, 10, 46}, new int[] {123, 47, 25, 16}),
    VERSION_38(38, "100110101001100100", new int[] {21616, 16816, 12016, 9136}, new int[] {30, 28, 30, 30}, new int[] {4, 13, 48, 42}, new int[] {122, 46, 24, 15}, new int[] {18, 32, 14, 32}, new int[] {123, 47, 25, 16}),
    VERSION_39(39, "100111010101000001", new int[] {22496, 17728, 12656, 9776}, new int[] {30, 28, 30, 30}, new int[] {20, 40, 43, 10}, new int[] {117, 47, 24, 15}, new int[] {4, 7, 22, 67}, new int[] {118, 48, 25, 16}),
    VERSION_40(40, "101000110001101001", new int[] {23648, 18672, 13328, 10208}, new int[] {30, 28, 30, 30}, new int[] {19, 18, 34, 20}, new int[] {118, 47, 24, 15}, new int[] {6, 31, 34, 61}, new int[] {119, 48, 25, 16});

    private final int version;
    private final String identity;
    private final int[] bits;
    private final int[] eccCodewordsPerBlock;
    private final int[] blocksInGroup1;
    private final int[] codewordsInGroup1;
    private final int[] blocksInGroup2;
    private final int[] codewordsInGroup2;

    Version(
        int version,
        String identity,
        int[] bits,
        int[] eccCodewordsPerBlock,
        int[] blocksInGroup1,
        int[] codewordsInGroup1,
        int[] blocksInGroup2,
        int[] codewordsInGroup2
    ) {
        this.version = version;
        this.identity = identity;
        this.bits = bits;
        this.eccCodewordsPerBlock = eccCodewordsPerBlock;
        this.blocksInGroup1 = blocksInGroup1;
        this.codewordsInGroup1 = codewordsInGroup1;
        this.blocksInGroup2 = blocksInGroup2;
        this.codewordsInGroup2 = codewordsInGroup2;
    }

    public int getVersion() {
        return version;
    }

    public int getSize() {
        return (((version-1)*4)+21);
    }

    public String getIdentity() {
        return identity;
    }

    public int getBits(Quality quality) {
        return bits[quality.getPlacement()];
    }

    public int getEccCodewordsPerBlock(Quality quality) {
        return eccCodewordsPerBlock[quality.getPlacement()];
    }

    public int getBlocksInGroup1(Quality quality) {
        return blocksInGroup1[quality.getPlacement()];
    }

    public int getCodewordsInGroup1(Quality quality) {
        return codewordsInGroup1[quality.getPlacement()];
    }

    public int getBlocksInGroup2(Quality quality) {
        return blocksInGroup2[quality.getPlacement()];
    }

    public int getCodewordsInGroup2(Quality quality) {
        return codewordsInGroup2[quality.getPlacement()];
    }
}
