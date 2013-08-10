package ui.action.handlers;

import ui.WindowFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles User actions on Stream Button
 * @author Aaron Hayes
 */
public class StreamButtonActionHandler {

    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public StreamButtonActionHandler(WindowFrame wf) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Stream!!");
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerStream(a);
    }
}
