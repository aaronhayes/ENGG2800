package ui.listeners.action.event.handlers;

import ui.WindowFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Handles User actions on Save Button
 * @author Aaron Hayes
 */
public class SaveButtonActionHandler {
    private WindowFrame windowFrame;
    static private final String EXT = ".bmp";

    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public SaveButtonActionHandler(WindowFrame wf) {
        windowFrame = wf;

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        ImageIO.write(windowFrame.getBitmapPanel().getDisplayBitmap(), "bmp", file);
                    } catch (IOException err) {
                        System.err.println("Unable to Save File");
                    }
                }
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerSave(a);
    }
}
