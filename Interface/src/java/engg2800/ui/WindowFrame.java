package engg2800.ui;

import engg2800.image.TransmittedImage;
import engg2800.image.processing.EdgeDetection;
import engg2800.ui.listeners.focus.handlers.PortComboBoxFocusHandler;
import engg2800.ui.listeners.item.handlers.PortComboBoxItemHandler;
import engg2800.ui.listeners.action.event.handlers.*;
import engg2800.ui.listeners.list.handlers.ListSelectionItemHandler;
import engg2800.ui.panels.*;
import engg2800.usb.SerialPortConnection;

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
    private ListPanel listPanel;

    private SerialPortConnection serialPortConnection;

    private ArrayList<TransmittedImage> images;
    private ArrayList<Integer> selectedImages;

    private TransmittedImage currentImage;
    private boolean edge = false;

    /**
     * Create a new Window Frame
     */
    public WindowFrame() {
        setTitle("MTV - ENGG2800 - Team 23");
        setLayout(new BorderLayout());
        new WindowActionHandler(this);
        serialPortConnection = new SerialPortConnection(this);
        addPanels();
        setBounds(250, 150, 1024, 768);
        controlPanel.getButtonPanel().addItems(serialPortConnection.getAvailablePorts());
        images = new ArrayList<TransmittedImage>();
        selectedImages = new ArrayList<Integer>();
    }

    /**
     * Add Panels to WindowFrame
     */
    private void addPanels() {
        addTitlePanel();
        addBitmapPanel();
        addSliderPanel();
        addControlPanel();
        addListPanel();
        pack();
    }

    /**
     * Add the List panel to the frame
     */
    private void addListPanel(){
        listPanel = new ListPanel();
        add(listPanel, BorderLayout.WEST);
        new ListSelectionItemHandler(this);
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
        new LoadButtonActionHandler(this);
        new PanoramaButtonActionHandler(this);
        new PortComboBoxItemHandler(this);
        new PortComboBoxFocusHandler(this);
        new BitmapActionHandler(this);
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
     * Get List of Transmitted Images
     */
    public ListPanel getList() {
        return listPanel;
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
     * Add another engg2800.image to the list
     * @param image Transmitted Image to be added
     */
    public void addImage(TransmittedImage image) {
        System.out.println("added engg2800.image");
        images.add(image);
        listPanel.addImageToList(getNumberTransmittedImages());
        listPanel.setSelection(listPanel.getListSize() - 1);
        bitmapPanel.updateImage(image.getBufferedImage());
        listPanel.clearSelections();
        listPanel.setSelection(listPanel.getListSize() - 1);
        currentImage = image;
        edge = false;
        EdgeDetection.dydx(image, this, (getNumberTransmittedImages() > 2));

        controlPanel.getInfoPanel().clearList();
        if (image.getStarsPoint() != null) {
            controlPanel.getInfoPanel().addFeature("Southern Cross (Center): ", image.getStarsPoint().getX(), image.getStarsPoint().getY());
        }
        if (image.getEarthPoint() != null) {
            controlPanel.getInfoPanel().addFeature("Earth (Center): ", image.getEarthPoint().getX(), image.getEarthPoint().getY());
        }
    }

    /**
     * Get the number of images that have been transmitted
     * @return images.size()
     */
    public int getNumberTransmittedImages() {
        return images.size();
    }

    /**
     * Update Selection list based on JList selection
     * @param i array location of Transmitted Image
     */
    public void putCurrentSelectedImage(int i) {
        if (!selectedImages.contains(i)) {
            selectedImages.add(i);
        }
    }

    /**
     * Clear currently selected images
     */
    public void clearCurrentSelectedImages() {
        selectedImages.clear();
    }

    /**
     *  Update Image display based on selection in list box
     */
    public void selectedNewImage() {
        try {
            int image = selectedImages.get(selectedImages.size() - 1);
            bitmapPanel.updateImage(images.get(image).getBufferedImage());
            currentImage = images.get(image);
            edge = false;
            controlPanel.getInfoPanel().clearList();
            if (currentImage.getStarsPoint() != null) {
                controlPanel.getInfoPanel().addFeature("Southern Cross (Center): ", currentImage.getStarsPoint().getX(), currentImage.getStarsPoint().getY());
            }
            if (currentImage.getEarthPoint() != null) {
                controlPanel.getInfoPanel().addFeature("Earth (Center): ", currentImage.getEarthPoint().getX(), currentImage.getEarthPoint().getY());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //TODO
        } catch (IndexOutOfBoundsException e) {
            //TODO
        }
    }

    /**
     * Get list of selected images for panorama mode
     * @return List of Transmitted Images
     */
    public ArrayList<TransmittedImage> getPanoramaImages() {
        ArrayList<TransmittedImage> panoramaImages = new ArrayList<TransmittedImage>();
        for (int i : selectedImages) {
            try {
                panoramaImages.add(images.get(i));
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        bitmapPanel.updateImage(panoramaImages.get(1).getBufferedImage());
        edge = false;
        return panoramaImages;
    }

    /**
     * Get number of images selected
     * @return number of images selected
     */
    public int getNumberSelected() {
        return selectedImages.size();
    }


    /**
     * Swap between edge detected engg2800.image and normal Transmitted engg2800.image
     */
    public void swapEdgeDisplay() {
        if (edge) {
            bitmapPanel.updateImage(currentImage.getBufferedImage());
        } else {
            bitmapPanel.updateImage(currentImage.getEdgeImage());
        }
        edge = !edge;
    }

    /**
     * Return the Transmitted Images of the Southern Cross
     * @return Southern Cross Transmitted Image
     */
    public TransmittedImage getStars() {
        return images.get(0);
    }

    /**
     * Return the Transmitted Images of the Earth
     * @return Earth Transmitted Image
     */
    public TransmittedImage getEarth() {
        return images.get(1);
    }
}
