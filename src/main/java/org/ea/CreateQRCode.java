package org.ea;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class CreateQRCode {
    public static void main(String[] args) {

        Quality quality = Quality.LEVEL_L;

        DataEncoder de = new DataEncoder();
        de.encode(quality, CharacterEncoding.NUMERIC, "3143158745");
//        de.encode(quality, CharacterEncoding.ALPLANUMERIC, "HELLO WORLD");
//        de.encode(quality, CharacterEncoding.BYTE, "Hello world");
//        de.encode(quality, CharacterEncoding.BYTE, "Testing 123, this is a test. Could be good, could be bad. Then again who knows. We all wonder, now and again. But we working on long. Where do it break?");
//        de.encode(quality, CharacterEncoding.BYTE, "The following table lists the number of error correction code words that you are required to generate for each version and error correction level of QR code. These values can be used to determine how many data bytes and error correction bytes are required for a given Reed-Solomon block.");
//        de.encode(quality, CharacterEncoding.BYTE, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus maximus ipsum vel faucibus dictum. Vestibulum vitae mattis est, non accumsan felis. Pellentesque leo arcu, pharetra non ante sed, faucibus dapibus odio. Duis sodales vestibulum lacus, in rutrum ipsum ornare sit amet. Phasellus facilisis tortor ac diam molestie, vel commodo massa cursus. Donec finibus enim ac erat porttitor volutpat. Aliquam eu ligula ac mauris auctor interdum id id urna. In scelerisque libero vel massa ultricies sagittis. Curabitur ut pharetra nunc, et lacinia est. Sed scelerisque arcu in euismod venenatis. Proin nec tempor est. Aliquam euismod dignissim felis id sodales. Nulla fermentum nulla non massa dictum, tincidunt hendrerit lacus maximus. Quisque eget tellus magna. Aliquam erat volutpat. Praesent hendrerit facilisis nulla, viverra pharetra velit. In lorem est, venenatis sit amet nulla at, sollicitudin ullamcorper dui. Donec ultricies elementum eros tincidunt vehicula. Aliquam sem nunc, tincidunt quis hendrerit at, lacinia at ligula. Sed vel suscipit turpis. Aliquam erat volutpat. Ut et nisi eget nunc ullamcorper feugiat. Maecenas ut lobortis lorem. Sed a blandit sapien. Nulla sit amet velit eget turpis viverra tincidunt quis vel lacus.");
        int[] dataBits = de.getMessage();

        QRCodeDrawer drawer = new QRCodeDrawer(de.getVersion(), 5);

        drawer.drawQRFeatures();

        int[] formatBits = new int[15];
        Arrays.fill(formatBits, Constants.FORMAT_BIT_0);
        Masker.drawFormatString(drawer.getQrCode(), de.getVersion(), formatBits);

        drawer.drawVersionString(de.getVersionBytes());

        drawer.drawDataString(dataBits, false);



        int[] qrCode = drawer.getQrCode();

        Masker m = new Masker();
        int optimialMask = m.calculateMask(qrCode, de.getVersion(), quality);

        qrCode = m.getMask(optimialMask);

        System.out.println("Using quality: " + quality.name());
        System.out.println("Using version: " + de.getVersion().getVersion());
        System.out.println("Using mask: " + optimialMask);
        System.out.println("Size: " + de.getMessage().length);

        try {
            drawer.createImage(qrCode, "qrcode.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
