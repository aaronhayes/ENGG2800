package ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Panel to display a bitmap
 * @author Aaron Hayes
 */
public class BitmapPanel extends JPanel {

    private BufferedImage image;

    /**
     * Basic constructor for a bitmap panel
     */
    public BitmapPanel() {
        super();
    }

    /**
     * Update the image
     * @param i BufferedImage bitmap
     */
    public void updateImage (BufferedImage i) {
        if (this.image != i) {
            this.image = i;
            repaint();
        }
    }

    /**
     * Get the image
     */
    public BufferedImage getBitmap() {
        return this.image;
    }

    /**
     *
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        BufferedImage img = getBitmap();

        if (img != null) {
            int x = (getWidth() - img.getWidth()) / 2;
            int y = (getHeight()- img.getHeight()) / 2;

            graphics.drawImage(img, x, y, this);
        }

        graphics.dispose();
    }
}
