package image.processing;

import java.awt.*;
import java.awt.image.BufferedImage;

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
}
