package ui.action.handlers;

import ui.WindowFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles User actions on Save Button
 * @author Aaron Hayes
 */
public class SaveButtonActionHandler {

    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public SaveButtonActionHandler(WindowFrame wf) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save!!");
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerSave(a);
    }
}
