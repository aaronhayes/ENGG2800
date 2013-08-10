package ui;

import ui.action.handlers.*;
import ui.panels.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * A window for displaying the user interface panels
 * @author Aaron Hayes
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
        new WindowActionHandler(this);
        addPanels();
        setBounds(250, 150, 750, 450);
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
            URL url = getClass().getResource("Placeholder.bmp");
            if (url != null) {
                bitmapPanel.updateImage(ImageIO.read(new File(url.getPath())));
            }
        } catch (IOException e) {
            System.err.println(e.toString() + " Leaving Placeholder Image off display.");
        }

        add(bitmapPanel, BorderLayout.CENTER);
    }

    /**
     * Add the Slider panel to the frame
     */
    private void addSliderPanel() {
        sliderPanel = new SliderPanel();
        add(sliderPanel, BorderLayout.EAST);
        new SliderActionHandler(this);
    }

    /**
     * Add the Control Panel to the frame
     */
    private void addControlPanel() {
        controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.SOUTH);
        new StreamButtonActionHandler(this);
        new SaveButtonActionHandler(this);
        new PanoramaButtonActionHandler(this);
    }

    /**
     * Get slider Panel
     * @return this.sliderPanel
     */
    public SliderPanel getSliderPanel() {
        return sliderPanel;
    }

    /**
     * Get content Panel
     * @return this.sliderPanel
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}
