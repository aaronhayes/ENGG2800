package ui.panels;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Panel to display a list of images
 * @author Aaron Hayes
 */
public class ListPanel extends JPanel {

    private DefaultListModel listModel;
    private JList list;
    private JScrollPane listScroller;
    private ListSelectionModel listSelectionModel;

    /**
     * Basic constructor
     */
    public ListPanel() {
        super();

        setLayout(new BorderLayout());

        listModel = new DefaultListModel();

        list = new JList(listModel);

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        listScroller = new JScrollPane(list);
        list.setPrototypeCellValue("Transmitted Image");
        listScroller.setPreferredSize(new Dimension(250, 100));

        listSelectionModel = list.getSelectionModel();

        add(list);
    }

    public void addImageToList(int i) {
        switch (i) {
            case 1: listModel.addElement("Southern-Cross"); break;
            case 2: listModel.addElement("Earth"); break;
            case 3: listModel.addElement("Placeholder"); break;
            default: listModel.addElement("Image " + i);
        }
    }

    public void clearSelections() {
        listSelectionModel.clearSelection();
    }

    public void setSelection(int i) {
        listSelectionModel.setSelectionInterval(i, i);
    }

    public int getListSize() {
        return listModel.getSize();
    }

    public void addActionHandler(ListSelectionListener listener) {
        listSelectionModel.addListSelectionListener(listener);
    }
}
