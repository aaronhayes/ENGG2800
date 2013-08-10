package ui.panels;

import javax.swing.*;
import ui.panels.control.*;

/**
 * Panel to display the control section
 * @author Aaron Hayes
 */
public class ControlPanel extends JPanel {

    private ButtonPanel buttonPanel;
    private InfoPanel infoPanel;

    /**
     * Basic constructor
     */
    public ControlPanel() {
        super();

        buttonPanel = new ButtonPanel();
        infoPanel = new InfoPanel();
    }
}
