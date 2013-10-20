package ui.listeners.action.event.handlers;

import ui.WindowFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Handles user interactions with GUI screen.
 * Exit program when window is closed.
 * @author Aaron Hayes
 */
public class WindowActionHandler {

    private WindowFrame windowFrame;
    /**
     * Basic Constructor for handlers
     * @param wf WindowFrame user is interacting with
     */
    public WindowActionHandler(WindowFrame wf) {
        windowFrame = wf;

        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                windowFrame.getSerialPortConnection().stop();
                System.exit(0);
            }
        };

        wf.addWindowListener(wa);
    }
}
