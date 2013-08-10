package ui.action.handlers;

import ui.WindowFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles User actions on Panorama Button
 * @author Aaron Hayes
 */
public class PanoramaButtonActionHandler {

    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public PanoramaButtonActionHandler(WindowFrame wf) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Panorama!!");
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerPanorama(a);
    }
}
