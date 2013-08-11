package ui.panels.control;

import javax.swing.*;
import java.awt.event.ActionListener;

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

    /**
     * Add Action Listen To Stream Button
     * @param a ActionListener to be added to stream button
     */
    public void addActionListenerStream(ActionListener a) {
        stream.addActionListener(a);
    }

    /**
     * Add Action Listen To Save Button
     * @param a ActionListener to be added to save button
     */
    public void addActionListenerSave(ActionListener a) {
        save.addActionListener(a);
    }

    /**
     * Add Action Listen To Panorama Button
     * @param a ActionListener to be added to Panorama button
     */
    public void addActionListenerPanorama(ActionListener a) {
        panorama.addActionListener(a);
    }

    /**
     * Change the text on the stream button
     * @param status boolean Status of the USB stream
     */
    public void toggleStreamButtonText(boolean status) {
        if (status) {
            stream.setText("Stop Stream");
        } else {
            stream.setText("Start Stream");
        }
        stream.repaint();

    }
}

