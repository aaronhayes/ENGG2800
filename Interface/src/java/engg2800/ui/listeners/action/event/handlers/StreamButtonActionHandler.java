package engg2800.ui.listeners.action.event.handlers;

import engg2800.ui.WindowFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles User actions on Stream Button
 * @author Aaron Hayes
 */
public class StreamButtonActionHandler {
    private WindowFrame windowFrame;
    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public StreamButtonActionHandler(WindowFrame wf) {
        windowFrame = wf;

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowFrame.getSerialPortConnection().toggle();
                windowFrame.getControlPanel().getButtonPanel().toggleStreamButtonText(
                        windowFrame.getSerialPortConnection().getStatus());
            }
        };

        wf.getControlPanel().getButtonPanel().addActionListenerStream(a);
    }
}
