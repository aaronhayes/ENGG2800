package ui.listeners.action.event.handlers;

import image.TransmittedImage;
import image.processing.AdjustImageBrightness;
import image.processing.JoinBufferedImages;
import ui.WindowFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles User actions on Panorama Button
 * @author Aaron Hayes
 */
public class PanoramaButtonActionHandler {
    private WindowFrame windowFrame;
    static private final String EXT = ".bmp";

    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public PanoramaButtonActionHandler(WindowFrame wf) {
        windowFrame = wf;

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<TransmittedImage> images = windowFrame.getPanoramaImages();
                int num = windowFrame.getNumberSelected();
                BufferedImage bi = JoinBufferedImages.join(images, num);

                if (bi != null) {
                    int sliderValue = windowFrame.getSliderValue();
                    BufferedImage image = AdjustImageBrightness.AdjustGrayscale(bi, sliderValue);

                    JFileChooser fc = new JFileChooser();
                    fc.setDialogTitle("Save Panorama");
                    int returnVal = fc.showSaveDialog(windowFrame);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file;
                        if (fc.getSelectedFile().getAbsolutePath().endsWith(EXT)) {
                            file = fc.getSelectedFile();
                        } else {
                            file = new File(fc.getSelectedFile().getPath() + EXT);
                        }

                        try {
                            ImageIO.write(image, "bmp", file);
                        } catch (IOException err) {
                            System.err.println("Unable to Save File");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(windowFrame, "Please selected at least 3 Images from the list");
                }
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerPanorama(a);
    }
}
