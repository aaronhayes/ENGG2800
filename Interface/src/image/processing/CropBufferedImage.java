package image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Crop Buffered Images
 * @author Aaron Hayes
 */
public class CropBufferedImage {

    public static BufferedImage crop(BufferedImage image, int initX, int initY) {

        BufferedImage bufferedImage = new BufferedImage(image.getWidth() - initX, image.getHeight() - initY, BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = bufferedImage.getGraphics();

        g.drawImage(image, initX, initY, null);

        g.dispose();

        return bufferedImage;
    }

}
