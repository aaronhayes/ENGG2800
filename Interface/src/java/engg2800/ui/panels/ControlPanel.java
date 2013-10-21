package engg2800.ui.panels;

import javax.swing.*;
import engg2800.ui.panels.control.*;
import java.awt.*;

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
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.EAST);
    }

    /**
     * Get Button Panel
     * @return ButtonPanel
     */
    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * Get Info Panel
     * @return InfoPanel
     */
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }
}
