package ui;

import ui.panels.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * A window for displaying the user interface panels
 * @author Aaron Hayes
 * @version 0.1
 */
public class WindowFrame extends JFrame {

    private BitmapPanel bitmapPanel;
    private SliderPanel sliderPanel;
    private ControlPanel controlPanel;
    private TitlePanel titlePanel;

    /**
     * Create a new Window Frame
     */
    public WindowFrame() {
        setTitle("MTV - ENGG2800 - Team 23");
        setLayout(new BorderLayout());

        addPanels();
        closeListener();
        setBounds(250, 150, 750, 450);
    }

    /**
     * Add listener to respond to closing the window
     */
    private void closeListener() {
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(wa);
    }

    /**
     * Add Panels to WindowFrame
     */
    private void addPanels() {
        addTitlePanel();
        addBitmapPanel();
        addSliderPanel();
        addControlPanel();
        pack();
    }

    /**
     * Add the Title panel to the frame
     */
    private void addTitlePanel(){
        titlePanel = new TitlePanel();
        add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Add the bitmap panel to the frame
     */
    private void addBitmapPanel() {
        bitmapPanel = new BitmapPanel();

        try {
            bitmapPanel.updateImage(ImageIO.read(new File("A:\\Uni\\GIT\\ENGG2800\\Interface\\src\\ui\\Untitled.bmp")));
        } catch (IOException e) {
            //TODO
        }

        add(bitmapPanel, BorderLayout.CENTER);
    }

    /**
     * Add the Slider panel to the frame
     */
    private void addSliderPanel() {
        sliderPanel = new SliderPanel();
        add(sliderPanel, BorderLayout.EAST);
    }

    /**
     * Add the Control Panel to the frame
     */
    private void addControlPanel() {
        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
    }
}
