package ui.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Panel to display a slider control
 * @author Aaron Hayes
 */
public class SliderPanel extends JPanel {

    /* Setup Slider Parameters*/
    private static final int BRIGHTNESS_MIN = -150;
    private static final int BRIGHTNESS_MAX = 150;
    private static final int BRIGHTNESS_INIT = 0;
    private static final int TICK_SPACING = 10;

    private JSlider slider;
    private JLabel logo;

    /**
     * Basic constructor
     */
    public SliderPanel() {
        super();

        setLayout(new BorderLayout());

        slider = new JSlider (JSlider.VERTICAL, BRIGHTNESS_MIN, BRIGHTNESS_MAX, BRIGHTNESS_INIT);
        slider.setMajorTickSpacing(TICK_SPACING);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);

        try {
            URL url = getClass().getResource("brightness_logo.png");
            if (url != null) {
                ImageIcon img = new ImageIcon(ImageIO.read(new File(url.getPath())));
                logo = new JLabel(img);
                add(logo, BorderLayout.NORTH);
            }
        } catch (IOException e) {
            System.err.println(e.toString() + " Leaving Brightness Logo off Panel.");
            // Leave logo off panel
        }

        add(slider);
    }

    /**
     * Get Slider Value
     */
    public int getSliderValue() {
        return slider.getValue();
    }

    /**
     * Add change listener to slider
     */
    public void addChangeListener(ChangeListener l) {
        slider.addChangeListener(l);
    }

}
