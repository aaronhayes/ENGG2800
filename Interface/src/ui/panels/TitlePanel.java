package ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel to add heading to the GUI
 * @author Aaron Hayes
 */
public class TitlePanel extends JPanel {

    JLabel label;

    /**
     * Basic constructor
     */
    public TitlePanel() {
        super();
        label = new JLabel("Mini-TeleVision (MTV) - ENGG2800 - Team 23");
        label.setFont(new Font("Serif", Font.BOLD, 28));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.DARK_GRAY);
        add(label);
    }

}
