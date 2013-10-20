package ui.listeners.action.event.handlers;

import ui.WindowFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Handles User actions on Save Button
 * @author Aaron Hayes
 */
public class BitmapActionHandler {
    private WindowFrame windowFrame;

    /**
     * Basic constructor
     * @param wf WindowFrame
     */
    public BitmapActionHandler(WindowFrame wf) {
        windowFrame = wf;

        MouseListener a = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                windowFrame.swapEdgeDisplay();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        wf.getBitmapPanel().getBitmapLabel().addMouseListener(a);
    }
}
