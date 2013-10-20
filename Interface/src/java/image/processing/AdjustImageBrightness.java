package image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RescaleOp;
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
    public static BufferedImage Adjust(BufferedImage i, int val) {
        float f = (float) val / 100;
        RescaleOp op = new RescaleOp(f, 0.0f, null);
        return op.filter(i, null);
    }

    /**
     * Adjust the brightness of an image.
     * @param i BufferedImage to be changed
     * @param val int brightness amount
     * @return adjusted BufferedImage
     */
    public static BufferedImage AdjustGrayscale(BufferedImage i, int val) {
        ColorModel colorModel = i.getColorModel();
        boolean alpha = i.isAlphaPremultiplied();
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

    /**
     * Adjust the brightness of an image.
     * @param img BufferedImage to be changed
     * @param val int brightness amount
     * @return adjusted BufferedImage
     */
    public static BufferedImage AdjustGrayscaleTest(BufferedImage img, int val) {
        byte[] bytes = BufferedImageToByteArray.Convert(img);

        System.out.println(bytes.length + " should be: " + (img.getHeight() * img.getWidth()));
        System.arraycopy(bytes, 0, bytes, 0, bytes.length);

        return ByteArrayToBufferedImage.Convert(bytes, img.getWidth(), img.getHeight());
    }
}
