package ui.action.handlers;

import ui.WindowFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Handles user interactions with GUI screen.
 * Exit program when window is closed.
 * @author Aaron Hayes
 */
public class WindowActionHandler {

    /**
     * Basic Constructor for handler
     * @param wf WindowFrame user is interacting with
     */
    public WindowActionHandler(WindowFrame wf) {
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        wf.addWindowListener(wa);
    }
}
