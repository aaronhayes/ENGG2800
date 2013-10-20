import image.TransmittedImage;
import ui.WindowFrame;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Start MTV display system
 * @author Aaron Hayes
 */
public class StreamViewer {

    /**
     * Entry into the MTV display system
     * @param args Program Arguments
     */
    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
        wf.setVisible(true);

        /* Load Reference/Placeholder images */
        try {
        	TransmittedImage image = new TransmittedImage(ImageIO.read(wf.getClass().getResourceAsStream("southerncross.png")));
            wf.addImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            TransmittedImage image = new TransmittedImage(ImageIO.read(wf.getClass().getResourceAsStream("earth.png")));
            wf.addImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
        	TransmittedImage image = new TransmittedImage(ImageIO.read(wf.getClass().getResourceAsStream("Placeholder2.bmp")));
            wf.addImage(image);
        } catch (IOException e) {
            System.err.println(e.toString() + " Leaving Placeholder Image off display.");
        }


    }
}
