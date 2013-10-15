package image.processing;

import com.sun.org.apache.xerces.internal.util.SymbolHash;

import java.awt.image.BufferedImage;

/**
 * Remove noise from an image
 * @author Aaron Hayes
 */
public class NoiseRemoval {
    public static final int ITTERATION_COUNT = 1;

    public static BufferedImage cleanup(BufferedImage image) {

        byte[] bytes = BufferedImageToByteArray.Convert(image);
        System.out.println(bytes.length);

        for (int z = 0; z < ITTERATION_COUNT; z++) {

            for (int y = 0; y < image.getHeight(); y++) {

                for (int x = 0; x < image.getWidth(); x++) {
                    bytes[(y * image.getWidth()) + x] = (byte) getAverage(bytes, x, y, image.getWidth(), image.getHeight());
                }

            }
        }

        return ByteArrayToBufferedImage.Convert(bytes, image.getWidth(), image.getHeight());

    }

    private static int getAverage(byte[] bytes, int x, int y, int width, int height) {

        int num = 0;
        int sum = 0;

        try {
            sum += bytes[(y * width) + x];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            sum += bytes[((y + 1) * width) + x];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            sum += bytes[((y - 1) * width) + x];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        try {
            sum += bytes[((y - 1) * width) + (x - 1)];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            sum += bytes[((y - 1) * width) + (x + 1)];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            sum += bytes[((y + 1) * width) + (x + 1)];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        try {
            sum += bytes[((y + 1) * width) + (x - 1)];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        try {
            sum += bytes[(y * width) + (x - 1)];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            sum += bytes[(y * width) + (x + 1)];
            num++;
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        return sum / num;


    }
}
