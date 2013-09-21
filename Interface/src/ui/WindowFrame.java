package ui;

import image.TransmittedImage;
import ui.listeners.focus.handlers.PortComboBoxFocusHandler;
import ui.listeners.item.handlers.PortComboBoxItemHandler;
import ui.listeners.action.event.handlers.*;
import ui.panels.*;
import usb.SerialPortConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A window for displaying the user interface panels
 * @author Aaron Hayes
 */
public class WindowFrame extends JFrame {

    private BitmapPanel bitmapPanel;
    private SliderPanel sliderPanel;
    private ControlPanel controlPanel;
    private TitlePanel titlePanel;

    private SerialPortConnection serialPortConnection;

    private ArrayList<TransmittedImage> images;

    /**
     * Create a new Window Frame
     */
    public WindowFrame() {
        setTitle("MTV - ENGG2800 - Team 23");
        setLayout(new BorderLayout());
        new WindowActionHandler(this);
        serialPortConnection = new SerialPortConnection(this);
        addPanels();
        setBounds(250, 150, 750, 450);
        controlPanel.getButtonPanel().addItems(serialPortConnection.getAvailablePorts());
        images = new ArrayList<TransmittedImage>();
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
        bitmapPanel = new BitmapPanel(this);

        try {
            URL url = getClass().getResource("Placeholder2.bmp");
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
        new PortComboBoxItemHandler(this);
        new PortComboBoxFocusHandler(this);
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

    /**
     * Get Bitmap Panel
     * @return this.bitmapPanel
     */
    public BitmapPanel getBitmapPanel() {
        return bitmapPanel;
    }

    /**
     * Get SerialPortListener
     * @return SerialPortListener
     */
    public SerialPortConnection getSerialPortConnection() {
        return serialPortConnection;
    }

    /**
     * Get List of Images Received from Transmission
     * @return images
     */
    public ArrayList<TransmittedImage> getImages() {
        return images;
    }

    /**
     * Get current slider value
     * @return slider value
     */
    public int getSliderValue() {
        return (sliderPanel != null ? sliderPanel.getSliderValue() : 0);
    }

    /**
     * Add another image to the list
     * @param image Transmitted Image to be added
     */
    public void addImage(TransmittedImage image) {
        images.add(image);
        bitmapPanel.updateImage(image.getBufferedImage());
    }

    /**
     * Get the number of images that have been transmitted
     * @return images.size()
     */
    public int getNumberTransmittedImages() {
        return images.size();
    }
}
