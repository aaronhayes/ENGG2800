package image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Copy a BufferedImage
 * @author Aaron Hayes
 */
public class CopyBufferedImage {
    public static BufferedImage Copy(BufferedImage image) {
        ColorModel colorModel = image.getColorModel();
        boolean alpha = image.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(colorModel, raster, alpha, null);
    }

}
