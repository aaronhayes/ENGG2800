package engg2800.image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Convert a BufferedImage to a Byte[]
 * @author Aaron Hayes
 */
public class BufferedImageToByteArray {

    /**
     * Convert engg2800.image to byte array
     * @param image BufferedImage to convert
     */
    public static byte[] Convert(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        WritableRaster raster = image.getRaster();

        byte[] bytes = new byte[height * width];
        int[] pixelArray1 = new int[10];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                raster.getPixel(x, y, pixelArray1);
                bytes[(y * width) + x] = (byte) averagePixel(pixelArray1);
            }
        }

        return bytes;
    }

    /**
     * Find the average value of a array of pixels
     * @param pixels array of pixels
     * @return average of the array values
     */
    private static int averagePixel(int[] pixels) {
        return (pixels[0] + pixels[1] + pixels[3]) / 3;
    }

}
