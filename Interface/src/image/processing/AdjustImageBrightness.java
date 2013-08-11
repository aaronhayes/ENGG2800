package image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

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
        RescaleOp op = new RescaleOp(f, 0, null);
        return op.filter(i, null);
    }
}
