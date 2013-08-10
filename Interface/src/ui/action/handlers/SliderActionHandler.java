package ui.action.handlers;

import ui.panels.SliderPanel;
import ui.WindowFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Aaron Hayes
 */
public class SliderActionHandler {
    private SliderPanel sliderPanel;

    /**
     * Basic Constructor
     * @param wf WindowFrame
     */
    public SliderActionHandler(WindowFrame wf) {
        sliderPanel = wf.getSliderPanel();

        ChangeListener cl = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int val = sliderPanel.getSliderValue();
                /* Check if slider value is divisible by 10 */
                if (val % 10 == 0) {
                    System.out.println("Value Changed, now = " + val);
                }
            }
        };

        sliderPanel.addChangeListner(cl);
    }
}
