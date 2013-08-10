package ui.panels.control;

import javax.swing.*;

/**
 * Panel to display information to user
 * @author Aaron Hayes
 */
public class InfoPanel extends JPanel {

    JLabel label;
    /**
     * Basic constructor
     */
    public InfoPanel() {
        super();
        label = new JLabel("Feature Detected at Location (x, y)");
        add(label);
    }

}
