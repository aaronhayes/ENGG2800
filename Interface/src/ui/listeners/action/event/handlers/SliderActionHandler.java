package ui.listeners.action.event.handlers;

import image.processing.CopyBufferedImage;
import ui.panels.SliderPanel;
import ui.WindowFrame;
import image.processing.AdjustImageBrightness;

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
                    BufferedImage img = CopyBufferedImage.Copy(windowFrame.getBitmapPanel().getBitmap());
                    windowFrame.getBitmapPanel().displayNewImage(AdjustImageBrightness.Adjust(img, val));
                }
            }
        };

        sliderPanel.addChangeListner(cl);
    }
}
