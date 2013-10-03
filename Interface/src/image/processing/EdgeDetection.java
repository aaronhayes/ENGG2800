package image.processing;

import image.TransmittedImage;
import ui.WindowFrame;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * Preform Edge Detection on a transmitted image
 *  @author Aaron Hayes
 */
public class EdgeDetection {
    private final static double THETA = 0.8;

    public static void PreformEdgeDetection(TransmittedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int radius = (int)Math.ceil(Math.hypot(width, height));

    }

    public static void dydx(TransmittedImage image, WindowFrame windowFrame) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getBufferedImage().getRaster();
        int[] pixelArray1 = new int[10];
        int[] pixelArray2 = new int[10];
        int[] pixelArray3 = new int[10];
        int[] pixelArray4 = new int[10];
        byte[] bytes = new byte[height * width];
        double[] angle = new double[height * width];
        double[] magnitude = new double[height * width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int xn = x - 1;
                int xp = x + 1;
                if (xn < 0) xn = 0;
                if (xp >= width) xp = width - 1;

                int yn = y - 1;
                int yp = y + 1;
                if (yn < 0) yn = 0;
                if (yp >= height) yp = height - 1;
                // Get Pixel 1 to the left/right
                raster.getPixel(xn, y, pixelArray1);
                raster.getPixel(xp, y, pixelArray2);
                // Get Pixel 1 to the up/down
                raster.getPixel(x, yn, pixelArray3);
                raster.getPixel(x, yp, pixelArray4);

                int dx = Math.abs(averagePixel(pixelArray2) - averagePixel(pixelArray1));
                int dy = Math.abs(averagePixel(pixelArray4) - averagePixel(pixelArray3));


                //bytes[(y * width) + x] = (byte) (Math.sqrt(dx ^ 2 + dy ^ 2));
                bytes[(y * width) + x] = (byte) (dx + dy);
                magnitude[(y * width) + x] = (Math.sqrt(dx ^ 2 + dy ^ 2));
                angle[(y * width) + x] = Math.atan2(dy,dx);
            }
        }

        image.setAngle(angle);
        image.setMagnitude(magnitude);
        BufferedImage bufferedImage = ByteArrayToBufferedImage.Convert(bytes, width, height);

        image.setEdgeImage(bufferedImage);
    }

    private static int averagePixel(int[] pixels) {
        return (pixels[0] + pixels[1] + pixels[3]) / 3;
    }
}
