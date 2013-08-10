package ui.panels.control;

import javax.swing.*;
import java.awt.event.ActionEvent;
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

        setupListeners();
        add(stream);
        add(save);
        add(panorama);
    }

    /**
     *  Add Action Listeners To Each Button
     */
    private void setupListeners() {
        ActionListener a1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Stream!!");

            }
        };

        ActionListener a2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save!!");
            }
        };

        ActionListener a3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("panorama!!");
            }
        };


        stream.addActionListener(a1);
        save.addActionListener(a2);
        panorama.addActionListener(a3);
    }
}

