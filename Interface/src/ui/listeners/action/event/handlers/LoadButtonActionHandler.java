package ui.listeners.action.event.handlers;

import image.TransmittedImage;
import ui.WindowFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
                int returnVal = fc.showSaveDialog(windowFrame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    File file = fc.getSelectedFile();

                    try {
                        TransmittedImage image = new TransmittedImage(ImageIO.read(file));
                        windowFrame.addImage(image);
                    } catch (IOException e1) {
                        System.out.println("Unable to Load Image");
                    }

                }
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerLoad(a);
    }
}
