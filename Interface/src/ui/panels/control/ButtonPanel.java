package ui.panels.control;

import javax.swing.*;

/**
 * Panel to display button controls
 * @author Aaron Hayes
 */
public class ButtonPanel extends JPanel {

    JButton stream;
    JButton save;
    JButton panorama;

    /**
     * Basic constructor
     */
    public ButtonPanel() {
        super();

        stream = new JButton("Start Stream");
        save = new JButton("Save Image");
        panorama = new JButton("Panorama");

        add(stream);
        add(save);
        add(panorama);
    }
}
