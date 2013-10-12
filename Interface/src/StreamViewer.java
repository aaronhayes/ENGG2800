import image.TransmittedImage;
import image.processing.ByteArrayToBufferedImage;
import ui.WindowFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Start MTV display system
 * @author Aaron Hayes
 */
public class StreamViewer {

    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
        wf.setVisible(true);
        int width = 320;
        int height = 240;

        try {
            URL url = wf.getClass().getResource("southerncross.png");
            if (url != null) {
                TransmittedImage image = new TransmittedImage(ImageIO.read(new File(url.getPath())));
                wf.addImage(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            URL url = wf.getClass().getResource("earth.png");
            if (url != null) {
                TransmittedImage image = new TransmittedImage(ImageIO.read(new File(url.getPath())));
                wf.addImage(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            URL url = wf.getClass().getResource("Placeholder2.bmp");
            if (url != null) {
                TransmittedImage image = new TransmittedImage(ImageIO.read(new File(url.getPath())));
                wf.addImage(image);
            }
        } catch (IOException e) {
            System.err.println(e.toString() + " Leaving Placeholder Image off display.");
        }



/*
        byte[] buffer = new byte[width * height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Make a 'crosshair' pattern
                buffer[(i * width) + j] = ((j == 50) || (i == 50)) ? (byte) 255 : 0;
            }
        }
        BufferedImage bufferedImage = ByteArrayToBufferedImage.Convert(buffer, width, height);
        wf.addImage(new TransmittedImage(bufferedImage));

        int width1 = 320;
        int height1 = 240;
        byte[] buffer1 = new byte[width1 * height1];

        for (int i = 0; i < height1; i++) {
            for (int j = 0; j < width1; j++) {
                // Make a 'crosshair' pattern
                buffer1[(i * width1) + j] = ((j == 75) || (i == 75)) ? (byte) 17 : (byte) 175;
            }
        }
        BufferedImage bufferedImage1 = ByteArrayToBufferedImage.Convert(buffer1, width, height);
        wf.addImage(new TransmittedImage(bufferedImage1));

        int width2 = 320;
        int height2 = 240;
        byte[] buffer2 = new byte[width * height];

        for (int i = 0; i < height2; i++) {
            for (int j = 0; j < width2; j++) {
                // Make a 'crosshair' pattern
                buffer2[(i * width2) + j] = ((j == 69) || (i == 69)) ? (byte) 175 : 65;
            }
        }
        BufferedImage bufferedImage2 = ByteArrayToBufferedImage.Convert(buffer2, width, height);
        wf.addImage(new TransmittedImage(bufferedImage2));


        int width3 = 320;
        int height3 = 240;
        byte[] buffer3 = new byte[width1 * height1];

        for (int i = 0; i < height3; i++) {
            for (int j = 0; j < width3; j++) {
                // Make a 'crosshair' pattern
                buffer3[(i * width3) + j] = ((j != 75) || (i == 75)) ? (byte) 100 : (byte) 200;
            }
        }
        BufferedImage bufferedImage3 = ByteArrayToBufferedImage.Convert(buffer3, width, height);
        wf.addImage(new TransmittedImage(bufferedImage3));

        int width4 = 320;
        int height4 = 240;
        byte[] buffer4 = new byte[width * height];

        for (int i = 0; i < height2; i++) {
            for (int j = 0; j < width2; j++) {
                // Make a 'crosshair' pattern
                buffer4[(i * width2) + j] = ((j % 3 == 0) || (i % 8 == 0)) ? (byte) 200 : 50;
            }
        }
        BufferedImage bufferedImage4 = ByteArrayToBufferedImage.Convert(buffer4, width, height);
        wf.addImage(new TransmittedImage(bufferedImage4));

        byte[] buffer5 = new byte[width * height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Make a 'crosshair' pattern
                buffer5[(i * width) + j] = ((j > 100 && j < 200) || (i > 100 && i < 200)) ? (byte) 200 : 50;
            }
        }
        BufferedImage bufferedImage5 = ByteArrayToBufferedImage.Convert(buffer5, width, height);
        wf.addImage(new TransmittedImage(bufferedImage5));


        byte[] buffer6 = new byte[width * height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Make a 'crosshair' pattern
                buffer6[(i * width) + j] = ((j > 100 && j < 200) && (i > 100 && i < 200)) ? (byte) 200 : 50;
            }
        }
        BufferedImage bufferedImage6 = ByteArrayToBufferedImage.Convert(buffer6, width, height);
        wf.addImage(new TransmittedImage(bufferedImage6));*/

    }
}
