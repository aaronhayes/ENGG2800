package ui.listeners.list.handlers;

import ui.WindowFrame;
import ui.panels.ListPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Event handler for selection of items on a list
 * @author Aaron Hayes
 */
public class ListSelectionItemHandler {

    private WindowFrame windowFrame;
    private ListPanel list;
    public ListSelectionItemHandler(WindowFrame wf) {
        windowFrame = wf;
        list = wf.getList();

        list.addActionHandler(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();

                int first = listSelectionModel.getMinSelectionIndex();
                int last = listSelectionModel.getMaxSelectionIndex();
                windowFrame.clearCurrentSelectedImages();
                for (int i = first; i <= last; i++) {
                    if (listSelectionModel.isSelectedIndex(i)) {
                        windowFrame.putCurrentSelectedImage(i);
                    }
                }
                windowFrame.selectedNewImage();
            }
        });
    }
}
