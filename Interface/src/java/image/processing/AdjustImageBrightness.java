package image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Adjust the brightness of an image
 * @author Aaron Hayes
 */
public class AdjustImageBrightness {

    /**
     * Adjust the brightness of an image.
     * @param i BufferedImage to be changed
     * @param val int brightness amount
     * @return adjusted BufferedImage
     */
    public static BufferedImage AdjustGrayscale(BufferedImage i, int val) {
        WritableRaster raster = i.getRaster();
        int[] pixelArray = new int[10];
        byte[] bytes = new byte[i.getHeight() * i.getWidth()];

        for (int j = 0; j < i.getHeight(); j++) {
            for (int k = 0; k < i.getWidth(); k++) {
                raster.getPixel(k, j, pixelArray);
                int newVal = pixelArray[0] + val;
                if (newVal > 255) newVal = 255;
                if (newVal < 0) newVal = 0;
                bytes[(j * i.getWidth()) + k] = (byte) newVal;
                raster.setPixel(k, j, pixelArray);
            }
        }
        return ByteArrayToBufferedImage.Convert(bytes, i.getWidth(), i.getHeight());
    }
}
