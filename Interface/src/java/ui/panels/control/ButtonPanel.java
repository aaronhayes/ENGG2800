package ui.panels.control;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;

/**
 * Panel to display button controls
 * @author Aaron Hayes
 */
public class ButtonPanel extends JPanel {

    private JButton load;
    private JButton stream;
    private JButton save;
    private JButton panorama;
    private JComboBox jComboBox;

    /**
     * Basic constructor
     */
    public ButtonPanel() {
        super();
        load = new JButton("Load Image");
        stream = new JButton("Start Stream");
        save = new JButton("Save Image");
        panorama = new JButton("Panorama");
        jComboBox = new JComboBox();

        load.setVerticalAlignment(SwingConstants.CENTER);
        stream.setVerticalAlignment(SwingConstants.CENTER);
        save.setVerticalAlignment(SwingConstants.CENTER);
        panorama.setVerticalAlignment(SwingConstants.CENTER);

        add(load);
        add(stream);
        add(save);
        add(panorama);
        add(jComboBox);
    }

    /**
     * Add Action Listen To Load Button
     * @param a ActionListener to be added to load button
     */
    public void addActionListenerLoad(ActionListener a) {
        load.addActionListener(a);
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
     * Add Item Listen To JComboBox
     * @param a ItemListener to be added to Combo Box
     */
    public void addItemListenerCombo(ItemListener a) {
        jComboBox.addItemListener(a);
    }

    /**
     * Add Focus Listen To JComboBox
     * @param a FocusListener to be added to Combo Box
     */
    public void addFocusListenerCombo(FocusListener a) {
        jComboBox.addFocusListener(a);
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

    /**
     * Clear ComboBox
     */
    public void clearComboBox() {
        jComboBox.removeAllItems();
    }

    /**
     * Add strings to combo box
     * @param s List of Strings to be added
     */
    public void addItems(String[] s) {
        for (String com : s) {
            jComboBox.addItem(com);
        }
    }

    /**
     * Get combo box value
     */
    public String getComboBoxValue() {
        return (String) jComboBox.getSelectedItem();
    }
}

