package engg2800.ui.listeners.item.handlers;

import engg2800.ui.WindowFrame;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Item Listener for COMS Port JComboBox
 * @author Aaron Hayes
 */
public class PortComboBoxItemHandler {
    private WindowFrame windowFrame;

    /**
     * Constructor for COMS port Item Handler
     * @param wf WindowFrame to preform actions on
     */
    public PortComboBoxItemHandler(WindowFrame wf) {
        windowFrame = wf;

        ItemListener i = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    windowFrame.getSerialPortConnection().updatePort(
                        windowFrame.getControlPanel().getButtonPanel().getComboBoxValue());
                }
            }
        };

        wf.getControlPanel().getButtonPanel().addItemListenerCombo(i);
    }
}
