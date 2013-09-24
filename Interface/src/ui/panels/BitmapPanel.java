package ui.panels;

import image.TransmittedImage;
import image.processing.AdjustImageBrightness;
import image.processing.CopyBufferedImage;
import ui.WindowFrame;

import javax.swing.*;
import java.awt.image.*;

/**
 * Panel to display a bitmap
 * @author Aaron Hayes
 */
public class BitmapPanel extends JPanel {

    private BufferedImage image;
    private BufferedImage display;
    private JLabel bitmap;
    private WindowFrame windowFrame;
    /**
     * Basic constructor for a bitmap panel
     */
    public BitmapPanel(WindowFrame wf) {
        super();
        bitmap = new JLabel();
        bitmap.setSize(TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT);
        add(bitmap);
        setSize(TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT);
        windowFrame = wf;
    }

    /**
     * Update the image
     * @param i BufferedImage bitmap
     */
    public void updateImage (BufferedImage i) {
        if (image != i) {
            image = i;
            displayNewImage(image);
        }
    }

    /**
     * Get the original image
     */
    public BufferedImage getBitmap() {
        return image;
    }

    /**
     * Get display image
     */
    public BufferedImage getDisplayBitmap() {
        return display;
    }

    /**
     * Display a new image.
     * @param i BufferedImage
     */
    public void displayNewImage (BufferedImage i) {
        display = CopyBufferedImage.Copy(i);
        bitmap.setIcon(new ImageIcon(AdjustImageBrightness.AdjustGrayscale(i,windowFrame.getSliderValue())));
        bitmap.repaint();
    }
}
