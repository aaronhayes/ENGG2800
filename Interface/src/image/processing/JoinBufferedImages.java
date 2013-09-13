package image.processing;

import image.TransmittedImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Join two BufferedImage
 * @author Aaron Hayes
 */
public class JoinBufferedImages {

    /**
     * Join three BufferedImages to make one.
     * @param a BufferedImage 1
     * @param b BufferedImage 2
     * @param c BufferedImage 3
     * @param offsetA offset from image 1 to 2
     * @param offsetB offset from image 2 to 3
     * @return BufferedImage
     */
    public static BufferedImage join(BufferedImage a, BufferedImage b, BufferedImage c, int offsetA, int offsetB) {
        int width = a.getWidth() + b.getWidth() + c.getWidth() - offsetA - offsetB;
        BufferedImage result = new BufferedImage(width, a.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = result.getGraphics();
        g.drawImage(a, 0, 0, null);
        g.drawImage(b, a.getWidth() - offsetA, 0, null);
        g.drawImage(c, width - c.getWidth(), 0, null);
        g.dispose();

        return result;
    }

    public static BufferedImage join(ArrayList<TransmittedImage> images, int num) {
        if (images.size() < num) return null;
        TransmittedImage[] transmittedImages = new TransmittedImage[num];

        int width = 0;
        int height = 0;
        for (int x = 0; x < num; x++) {
            transmittedImages[x] = images.get(images.size() - num + x);

            if (x > 0) {
                width += transmittedImages[x].getWidth();
                width -= transmittedImages[x].getXOffset();
            } else {
                width += transmittedImages[x].getWidth();
                height += transmittedImages[x].getHeight();
            }
        }

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = result.getGraphics();
        g.drawImage(transmittedImages[0].getBufferedImage(), 0, 0, null);
        int currentWidth = transmittedImages[0].getWidth();
        for (int x = 1; x < num; x++) {
            g.drawImage(transmittedImages[x].getBufferedImage(), currentWidth - transmittedImages[x].getXOffset(), 0, null);
            currentWidth += (transmittedImages[x].getWidth() - transmittedImages[x].getWidth());
        }
        g.dispose();

        return result;
    }
}
