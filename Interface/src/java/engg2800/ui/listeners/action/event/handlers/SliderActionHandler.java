package engg2800.ui.listeners.action.event.handlers;

import engg2800.image.processing.CopyBufferedImage;
import engg2800.ui.panels.SliderPanel;
import engg2800.ui.WindowFrame;
import engg2800.image.processing.AdjustImageBrightness;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.image.BufferedImage;

/**
 * Handle Slider Value Changes
 * @author Aaron Hayes
 */
public class SliderActionHandler {
    private SliderPanel sliderPanel;
    private WindowFrame windowFrame;
    /**
     * Basic Constructor
     * @param wf WindowFrame
     */
    public SliderActionHandler(WindowFrame wf) {
        windowFrame = wf;
        sliderPanel = windowFrame.getSliderPanel();

        ChangeListener cl = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int val = sliderPanel.getSliderValue();
                /* Check if slider value is divisible by 10 */
                if (val % 10 == 0) {
                    BufferedImage img = CopyBufferedImage.copy(windowFrame.getBitmapPanel().getBitmap());
                    windowFrame.getBitmapPanel().displayNewImage(AdjustImageBrightness.AdjustGrayscale(img, val));
                }
            }
        };

        sliderPanel.addChangeListener(cl);
    }
}
