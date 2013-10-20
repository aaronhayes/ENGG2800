package image.processing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Convert a BufferedImage to a Byte[]
 * @author Aaron Hayes
 */
public class BufferedImageToByteArray {

    /**
     * Convert image
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
        /*
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes;
        try {
            ImageIO.write(image, "bmp", baos);
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            return null;
        } */
        return bytes;
    }

    private static int averagePixel(int[] pixels) {
        return (pixels[0] + pixels[1] + pixels[3]) / 3;
    }

}
