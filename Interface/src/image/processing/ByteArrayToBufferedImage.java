package image.processing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Convert a Byte[] to a BufferedImage
 * @author Aaron Hayes
 */
public class ByteArrayToBufferedImage {

    /**
     * Convert image
     * @param bytes Byte[] to be converted
     */
    public static BufferedImage Convert(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            return null;
        }
    }

}
