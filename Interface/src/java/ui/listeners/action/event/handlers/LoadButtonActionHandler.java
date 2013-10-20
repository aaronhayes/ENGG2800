package ui.listeners.action.event.handlers;

import image.TransmittedImage;
import image.processing.ByteArrayToBufferedImage;
import ui.WindowFrame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Handles User actions on Load Button
 * @author Aaron Hayes
 */
public class LoadButtonActionHandler {
    private WindowFrame windowFrame;

    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public LoadButtonActionHandler(WindowFrame wf) {
        windowFrame = wf;

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Load Image From File");
                int returnVal = fc.showOpenDialog(windowFrame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    FileInputStream in = null;
                    File file = fc.getSelectedFile();

                    if (file.getAbsolutePath().endsWith(".raw") || file.getAbsolutePath().endsWith(".bytes") || !file.getAbsolutePath().contains("")) {
                        try {
                            in = new FileInputStream(file);
                            int c;

                            byte[] bytes = new byte[TransmittedImage.IMG_HEIGHT * TransmittedImage.IMG_WIDTH];

                            int count = 0;
                            while ((c = in.read()) != -1) {
                                bytes[count++] = (byte) (c & 0xFF);
                            }

                            BufferedImage bufferedImage = ByteArrayToBufferedImage.Convert(bytes, TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT);
                            windowFrame.addImage(new TransmittedImage(bufferedImage, TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT));

                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ArrayIndexOutOfBoundsException e1) {
                            System.err.println("Invalid file format, must be 8 bit 320 x 240 pixels");
                        }
                    } else {
                        try {
                        	BufferedImage bufferedimage = ImageIO.read(file);
                            TransmittedImage image = new TransmittedImage(bufferedimage, bufferedimage.getWidth(), bufferedimage.getHeight());
                            windowFrame.addImage(image);
                        } catch (IOException e1) {
                            System.out.println("Unable to Load Image");
                        }
                    }
                }
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerLoad(a);
    }
}
