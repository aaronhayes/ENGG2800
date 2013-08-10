package ui.panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
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
    static final int BRIGHTNESS_MIN = 0;
    static final int BRIGHTNESS_MAX = 100;
    static final int BRIGHTNESS_INIT = 50;
    static final int TICK_SPACING = 10;

    JSlider slider;
    JLabel logo;

    /**
     * Basic constructor
     */
    public SliderPanel() {
        super();

        setLayout(new BorderLayout());

        slider = new JSlider (JSlider.VERTICAL, BRIGHTNESS_MIN, BRIGHTNESS_MAX, BRIGHTNESS_INIT);
        setupListener();
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
     * Listen to slider
     */
    private void setupListener() {
        ChangeListener cl = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int val = slider.getValue();

                /* Check if slider value is divisible by 10 */
                if (val % 10 == 0 ) {
                    System.out.println("Value Changed, now = " + val);
                }
            }
        };
        slider.addChangeListener(cl);
    }

}
