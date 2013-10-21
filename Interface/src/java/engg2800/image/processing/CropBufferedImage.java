package engg2800.image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Crop Buffered Images
 * @author Aaron Hayes
 */
public class CropBufferedImage {

    /**
     * Crop Buffered Image
     *  Will result in an engg2800.image size of (x - initX, y - initY)
     * @param image Buffed Image to Crop
     * @param initX initial x offset
     * @param initY initial y offset
     * @return new cropped buffed Image
     */
    public static BufferedImage crop(BufferedImage image, int initX, int initY) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth() - initX, image.getHeight() - initY, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, initX, initY, null);
        g.dispose();

        return bufferedImage;
    }

}
