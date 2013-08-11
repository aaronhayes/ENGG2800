package ui.panels;

import image.processing.CopyBufferedImage;

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

    /**
     * Basic constructor for a bitmap panel
     */
    public BitmapPanel() {
        super();
        bitmap = new JLabel();
        bitmap.setSize(320, 240);
        add(bitmap);
        setSize(320, 240);
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
     * Display a new image.
     * @param i BufferedImage
     */
    public void displayNewImage (BufferedImage i) {
        display = CopyBufferedImage.Copy(i);
        bitmap.setIcon(new ImageIcon(display));
        bitmap.repaint();
    }
}
