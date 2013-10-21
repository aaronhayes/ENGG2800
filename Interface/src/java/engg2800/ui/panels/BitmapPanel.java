package engg2800.ui.panels;

import engg2800.image.TransmittedImage;
import engg2800.image.processing.AdjustImageBrightness;
import engg2800.image.processing.CopyBufferedImage;
import engg2800.ui.WindowFrame;

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
     * Update the engg2800.image
     * @param i BufferedImage bitmap
     */
    public void updateImage (BufferedImage i) {
        if (image != i) {
            image = i;
            displayNewImage(image);
        }
    }

    /**
     * Get the original engg2800.image
     */
    public BufferedImage getBitmap() {
        return image;
    }

    /**
     * Get display engg2800.image
     */
    public BufferedImage getDisplayBitmap() {
        return display;
    }

    /**
     * Display a new engg2800.image.
     * @param i BufferedImage
     */
    public void displayNewImage (BufferedImage i) {
        display = CopyBufferedImage.copy(i);
        bitmap.setIcon(new ImageIcon(AdjustImageBrightness.AdjustGrayscale(i,windowFrame.getSliderValue())));
        bitmap.repaint();
    }

    /**
     * Get Label containing Bitmap
     * @return bitmap JLabel
     */
    public JLabel getBitmapLabel() {
        return bitmap;
    }
}
