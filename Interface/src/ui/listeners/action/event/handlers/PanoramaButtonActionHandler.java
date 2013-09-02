package ui.listeners.action.event.handlers;

import image.processing.JoinBufferedImages;
import ui.WindowFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

                BufferedImage bi = JoinBufferedImages.join(windowFrame.getBitmapPanel().getBitmap(),
                        windowFrame.getBitmapPanel().getBitmap(), windowFrame.getBitmapPanel().getBitmap(), 0, 0);

                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(windowFrame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    File file;
                    if (fc.getSelectedFile().getAbsolutePath().endsWith(EXT)) {
                        file = fc.getSelectedFile();
                    } else {
                        file = new File(fc.getSelectedFile().getPath() + EXT);
                    }

                    try {
                        ImageIO.write(bi, "bmp", file);
                    } catch (IOException err) {
                        System.err.println("Unable to Save File");
                    }
                }
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerPanorama(a);
    }
}