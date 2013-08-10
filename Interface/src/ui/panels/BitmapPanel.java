package ui.panels;

import javax.swing.*;
import java.awt.image.*;

/**
 * Panel to display a bitmap
 * @author Aaron Hayes
 */
public class BitmapPanel extends JPanel {

    private BufferedImage image;
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

            RescaleOp op = new RescaleOp(1f, 0, null);
            image = op.filter(image, null);

            bitmap.setIcon(new ImageIcon(image));
            bitmap.repaint();
        }
    }

    /**
     * Get the image
     */
    public BufferedImage getBitmap() {
        return image;
    }
}
