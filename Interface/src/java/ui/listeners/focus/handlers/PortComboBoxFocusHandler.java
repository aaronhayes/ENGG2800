package ui.listeners.focus.handlers;

import ui.WindowFrame;

import java.awt.event.*;

/**
 * Item Listener for COMS Port JComboBox
 * @author Aaron Hayes
 */
public class PortComboBoxFocusHandler {
    private WindowFrame windowFrame;

    /**
     * Constructor for COMS Port ComboBox Focus Handler
     * @param wf WindowFrame to preform actions on
     */
    public PortComboBoxFocusHandler(WindowFrame wf) {
        windowFrame = wf;

        FocusListener a = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
               windowFrame.getControlPanel().getButtonPanel().clearComboBox();
               String[] ports = windowFrame.getSerialPortConnection().getAvailablePorts();
               if (ports.length > 0) {
                   windowFrame.getControlPanel().getButtonPanel().addItems(ports);
               }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        };

        wf.getControlPanel().getButtonPanel().addFocusListenerCombo(a);
    }
}
