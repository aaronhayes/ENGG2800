package image.processing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Convert a BufferedImage to a Byte[]
 * @author Aaron Hayes
 */
public class BufferedImageToByteArray {

    /**
     * Convert image
     * @param image BufferedImage to convert
     */
    public static byte[] Convert(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes;
        try {
            ImageIO.write(image, "bmp", baos);
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            return null;
        }
        return bytes;
    }

}
