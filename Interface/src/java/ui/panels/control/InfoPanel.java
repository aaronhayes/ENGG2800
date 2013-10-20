package ui.panels.control;

import javax.swing.*;

/**
 * Panel to display information to user
 * @author Aaron Hayes
 */
public class InfoPanel extends JPanel {
    private JList list;
    private DefaultListModel model;

    /**
     * Basic constructor
     */
    public InfoPanel() {
        super();
        model = new DefaultListModel();
        list = new JList(model);

        list.setFixedCellWidth(250);
        list.setBackground(UIManager.getColor("Panel.background"));
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        add(list);
    }

    /**
     * Add a feature to the list
     * @param s String Name of the feature
     * @param x int x location
     * @param y int y location
     */
    public void addFeature (String s, int x, int y) {
        model.addElement(s + " (" + x + "," + y + ")");
    }

    /**
     * Clear List
     */
    public void clearList() {
        model.removeAllElements();
    }

}
