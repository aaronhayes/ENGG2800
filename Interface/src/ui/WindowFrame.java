package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * A window for displaying the user interface
 * @author Aaron Hayes
 * @version 0.1
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class WindowFrame extends JFrame {

    private BitmapPanel bitmapPane;

    /**
     * Create a new Window Frame
     */
    public WindowFrame() {
        setTitle("MTV - Team 23 - ENGG2800");
        setBounds(300, 150, 500, 500);
        setSize(500, 500);
        bitmapPane = new BitmapPanel();

        try {
            bitmapPane.updateImage(ImageIO.read(new File("A:\\Uni\\GIT\\ENGG2800\\Interface\\src\\ui\\Untitled.bmp")));
        } catch (IOException e) {
            //TODO
        }

        add(bitmapPane);
        pack();

        closeListener();
    }

    /**
     * Add listener to respond to closing the window
     */
    private void closeListener() {
        WindowAdapter wa = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        addWindowListener(wa);
    }



}
