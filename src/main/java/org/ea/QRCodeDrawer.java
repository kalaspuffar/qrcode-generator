package org.ea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class QRCodeDrawer {
    private final Color emptyModule = Color.LIGHT_GRAY;
    private final int size;
    private final int[] qrCode;
    private final Version version;
    private final int moduleSize;

    public QRCodeDrawer(Version version, int moduleSize) {
        this.version = version;
        this.moduleSize = moduleSize;

        size = version.getSize();
        qrCode = new int[size * size];

        Arrays.fill(qrCode, getIntFromColor(emptyModule));
    }

    public static void main(String[] args) throws Exception {
        Version version = Version.VERSION_10;

        QRCodeDrawer drawer = new QRCodeDrawer(version, 5);
        drawer.drawFinderPatterns();
        drawer.drawAlignmentPatterns();
        drawer.drawTimingPatterns();
        drawer.drawDarkModule();
        Masker.drawFormatString(drawer.getQrCode(), version, drawer.getColorFormat());
        drawer.drawVersionString(drawer.getColorVersion());
        drawer.drawDataString(drawer.getColorData(), true);

        drawer.createImage(drawer.getQrCode(), "qrcode.png");
    }

    private void drawFinderPatterns() {
        drawFinderPattern(0, 0);
        drawFinderPattern( version.getSize() - 7, 0);
        drawFinderPattern( 0, version.getSize() - 7);
    }

    public void createImage(int[] qrCode, String filename) throws IOException {
        int imgSize = (size + 8) * moduleSize;
        BufferedImage bi = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imgSize, imgSize);

        for(int i = 0; i < qrCode.length; i++) {
            g.setColor(getColorFromInt(qrCode[i]));

            g.fillRect(
                    (4 + i % size) * moduleSize,
                    (4 + Math.floorDiv(i, size)) * moduleSize,
                    moduleSize,
                    moduleSize
            );
        }
        ImageIO.write(bi, "PNG", new File(filename));
    }

    private void drawDarkModule() {
        qrCode[((4 * version.getVersion()) + 9) * size + 8] = getIntFromColor(Color.BLACK);
    }

    private int[] getColorFormat() {
        int[] formatBits = new int[15];
        formatBits[14] = getIntFromColor(new Color(0, 0, 180));
        formatBits[13] = getIntFromColor(new Color(0, 0, 200));
        formatBits[12] = getIntFromColor(new Color(0, 0, 220));
        formatBits[11] = getIntFromColor(new Color(0, 0, 240));
        formatBits[10] = getIntFromColor(new Color(20, 20, 255));
        formatBits[9] = getIntFromColor(new Color(40, 40, 255));
        formatBits[8] = getIntFromColor(new Color(60, 60, 255));
        formatBits[7] = getIntFromColor(new Color(80, 80, 255));
        formatBits[6] = getIntFromColor(new Color(100, 100, 255));
        formatBits[5] = getIntFromColor(new Color(120, 120, 255));
        formatBits[4] = getIntFromColor(new Color(140, 140, 255));
        formatBits[3] = getIntFromColor(new Color(160, 160, 255));
        formatBits[2] = getIntFromColor(new Color(180, 180, 255));
        formatBits[1] = getIntFromColor(new Color(200, 200, 255));
        formatBits[0] = getIntFromColor(new Color(220, 220, 255));
        return formatBits;
    }

    private int[] getColorVersion() {
        int[] versionBits = new int[18];
        versionBits[17] = getIntFromColor(new Color(120, 0, 0));
        versionBits[16] = getIntFromColor(new Color(140, 0, 0));
        versionBits[15] = getIntFromColor(new Color(160, 0, 0));
        versionBits[14] = getIntFromColor(new Color(180, 0, 0));
        versionBits[13] = getIntFromColor(new Color(200, 0, 0));
        versionBits[12] = getIntFromColor(new Color(220, 0, 0));
        versionBits[11] = getIntFromColor(new Color(240, 0, 0));
        versionBits[10] = getIntFromColor(new Color(255, 20, 20));
        versionBits[9] = getIntFromColor(new Color(255, 40, 40));
        versionBits[8] = getIntFromColor(new Color(255, 60, 60));
        versionBits[7] = getIntFromColor(new Color(255, 80, 80));
        versionBits[6] = getIntFromColor(new Color(255, 100, 100));
        versionBits[5] = getIntFromColor(new Color(255, 120, 120));
        versionBits[4] = getIntFromColor(new Color(255, 140, 140));
        versionBits[3] = getIntFromColor(new Color(255, 160, 160));
        versionBits[2] = getIntFromColor(new Color(255, 180, 180));
        versionBits[1] = getIntFromColor(new Color(255, 200, 200));
        versionBits[0] = getIntFromColor(new Color(255, 220, 220));
        return versionBits;
    }

    private int[] getColorData() {
        int[] dataBits = new int[18];
        dataBits[17] = getIntFromColor(new Color(0, 120, 0));
        dataBits[16] = getIntFromColor(new Color(0, 140, 0));
        dataBits[15] = getIntFromColor(new Color(0, 160, 0));
        dataBits[14] = getIntFromColor(new Color(0, 180, 0));
        dataBits[13] = getIntFromColor(new Color(0, 200, 0));
        dataBits[12] = getIntFromColor(new Color(0, 220, 0));
        dataBits[11] = getIntFromColor(new Color(0, 240, 0));
        dataBits[10] = getIntFromColor(new Color(20, 255, 20));
        dataBits[9] = getIntFromColor(new Color(40, 255, 40));
        dataBits[8] = getIntFromColor(new Color(60, 255, 60));
        dataBits[7] = getIntFromColor(new Color(80, 255, 80));
        dataBits[6] = getIntFromColor(new Color(100, 255, 100));
        dataBits[5] = getIntFromColor(new Color(120, 255, 120));
        dataBits[4] = getIntFromColor(new Color(140, 255, 140));
        dataBits[3] = getIntFromColor(new Color(160, 255, 160));
        dataBits[2] = getIntFromColor(new Color(180, 255, 180));
        dataBits[1] = getIntFromColor(new Color(200, 255, 200));
        dataBits[0] = getIntFromColor(new Color(220, 255, 220));
        return dataBits;
    }
// [16711680, 16711680, 16711680, 65535, 65535, 65535, 65535, 65535, 16711680, 16711680, 65535, 16711680, 16711680, 65535, 16711680, 65535, 16711680, 16711680]
    public void drawVersionString(int[] colorVersion) {
        if(version.getVersion() < 7) return;
        int versionIt = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                qrCode[(5 - i) * size + size - 9 - j] = colorVersion[versionIt];
                qrCode[(size - 9 - j) * size + 5 - i] = colorVersion[versionIt];
                versionIt++;
            }
       }
    }

    public void drawDataString(int[] colorData, boolean fill) {
        boolean drawingDown = false;
        int colorDataIt = 0;
        for (int x = size - 1; x > -1; x -= 2) {
            if (x == 6) x--;
            for (int y = drawingDown ? 0 : size - 1; y > -1 && y < size; y += drawingDown ? 1 : -1) {
                if (qrCode[y * size + x] == getIntFromColor(emptyModule)) {
                    qrCode[y * size + x] = colorData[colorDataIt];
                    colorDataIt++;
                    if(colorDataIt > colorData.length - 1) {
                        if(!fill) return;
                        colorDataIt = 0;
                    }
                }
                if (x > 0 && qrCode[y * size + x - 1] == getIntFromColor(emptyModule)) {
                    qrCode[y * size + x - 1] = colorData[colorDataIt];
                    colorDataIt++;
                    if(colorDataIt > colorData.length - 1) {
                        if(!fill) return;
                        colorDataIt = 0;
                    }
                }
            }
            drawingDown = !drawingDown;
        }
    }

    private void drawTimingPatterns() {
        boolean black = true;
        for(int xi = 0; xi < size; xi++) {
            if (qrCode[6 * size + xi] == getIntFromColor(emptyModule)) {
                qrCode[6 * size + xi] = getIntFromColor(black ? Color.BLACK : Color.WHITE);
            }
            if (qrCode[xi * size + 6] == getIntFromColor(emptyModule)) {
                qrCode[xi * size + 6] = getIntFromColor(black ? Color.BLACK : Color.WHITE);
            }
            black = !black;
        }
    }

    private void drawFinderPattern(int x, int y) {
        for(int yi = y - 1; yi < y + 8; yi++) {
            for(int xi = x - 1; xi < x + 8; xi++) {
                if(xi > size - 1 || yi > size - 1 || xi < 0 || yi < 0) continue;
                qrCode[yi * size + xi] = getIntFromColor(Color.WHITE);
            }
        }

        for(int yi = y; yi < y + 7; yi++) {
            for(int xi = x; xi < x + 7; xi++) {
                qrCode[yi * size + xi] = getIntFromColor(Color.BLACK);
            }
        }
        for(int yi = y + 1; yi < y + 6; yi++) {
            for(int xi = x + 1; xi < x + 6; xi++) {
                qrCode[yi * size + xi] = getIntFromColor(Color.WHITE);
            }
        }
        for(int yi = y + 2; yi < y + 5; yi++) {
            for(int xi = x + 2; xi < x + 5; xi++) {
                qrCode[yi * size + xi] = getIntFromColor(Color.BLACK);
            }
        }
    }

    private void drawAlignmentPatterns() {
        int[][] alignmentMatrix = new int[][] {
                new int[] {},
                new int[] {},
                new int[] {6, 18},
                new int[] {6, 22},
                new int[] {6, 26},
                new int[] {6, 30},
                new int[] {6, 34},
                new int[] {6, 22, 38},
                new int[] {6, 24, 42},
                new int[] {6, 26, 46},
                new int[] {6, 28, 50},
                new int[] {6, 30, 54},
                new int[] {6, 32, 58},
                new int[] {6, 34, 62},
                new int[] {6, 26, 46, 66},
                new int[] {6, 26, 48, 70},
                new int[] {6, 26, 50, 74},
                new int[] {6, 30, 54, 78},
                new int[] {6, 30, 56, 82},
                new int[] {6, 30, 58, 86},
                new int[] {6, 34, 62, 90},
                new int[] {6, 28, 50, 72, 94},
                new int[] {6, 26, 50, 74, 98},
                new int[] {6, 30, 54, 78, 102},
                new int[] {6, 28, 54, 80, 106},
                new int[] {6, 32, 58, 84, 110},
                new int[] {6, 30, 58, 86, 114},
                new int[] {6, 34, 62, 90, 118},
                new int[] {6, 26, 50, 74, 98, 122},
                new int[] {6, 30, 54, 78, 102, 126},
                new int[] {6, 26, 52, 78, 104, 130},
                new int[] {6, 30, 56, 82, 108, 134},
                new int[] {6, 34, 60, 86, 112, 138},
                new int[] {6, 30, 58, 86, 114, 142},
                new int[] {6, 34, 62, 90, 118, 146},
                new int[] {6, 30, 54, 78, 102, 126, 150},
                new int[] {6, 24, 50, 76, 102, 128, 154},
                new int[] {6, 28, 54, 80, 106, 132, 158},
                new int[] {6, 32, 58, 84, 110, 136, 162},
                new int[] {6, 26, 54, 82, 110, 138, 166},
                new int[] {6, 30, 58, 86, 114, 142, 170},
        };

        for(int y : alignmentMatrix[version.getVersion()]) {
            for(int x : alignmentMatrix[version.getVersion()]) {
                drawAlignmentPattern(qrCode, size, y, x);
            }
        }
    }

    private void drawAlignmentPattern(int[] qrCode, int size, int x, int y) {
        for(int yi = y - 2; yi < y + 3; yi++) {
            for (int xi = x - 2; xi < x + 3; xi++) {
                if(qrCode[yi * size + xi] != getIntFromColor(emptyModule)) return;
            }
        }

        for(int yi = y - 2; yi < y + 3; yi++) {
            for(int xi = x - 2; xi < x + 3; xi++) {
                qrCode[yi * size + xi] = getIntFromColor(Color.BLACK);
            }
        }
        for(int yi = y - 1; yi < y + 2; yi++) {
            for(int xi = x - 1; xi < x + 2; xi++) {
                qrCode[yi * size + xi] = getIntFromColor(Color.WHITE);
            }
        }
        qrCode[y * size + x] = getIntFromColor(Color.BLACK);
    }

    private Color getColorFromInt(int rgb) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        return new Color(red, green, blue);
    }

    public static int getIntFromColor(Color c) {
        int rgb = c.getRed();
        rgb = (rgb << 8) + c.getGreen();
        rgb = (rgb << 8) + c.getBlue();
        return rgb;
    }

    public void drawQRFeatures() {
        drawFinderPatterns();
        drawAlignmentPatterns();
        drawTimingPatterns();
        drawDarkModule();
    }

    public int[] getQrCode() {
        return qrCode;
    }
}
