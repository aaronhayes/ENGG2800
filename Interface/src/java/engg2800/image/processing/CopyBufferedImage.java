package engg2800.image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Copy a BufferedImage
 * @author Aaron Hayes
 */
public class CopyBufferedImage {

    /**
     * Make a copy of a buffered Image
     * @param image Buffered Image to Copy
     * @return new copy of engg2800.image
     */
    public static BufferedImage copy(BufferedImage image) {
        ColorModel colorModel = image.getColorModel();
        boolean alpha = image.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(colorModel, raster, alpha, null);
    }

}
