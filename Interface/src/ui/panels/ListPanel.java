package ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel to display a list of images
 * @author Aaron Hayes
 */
public class ListPanel extends JPanel {

    private DefaultListModel listModel;
    private JList list;
    private JScrollPane listScroller;

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
        listScroller.setPreferredSize(new Dimension(250, 80));

        add(list);
    }

    public void addImageToList(int i) {
        listModel.addElement("Transmitted Image " + i);
    }
}
