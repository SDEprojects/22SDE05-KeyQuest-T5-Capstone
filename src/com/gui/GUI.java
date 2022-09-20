package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GUI {
    private JFrame window;
    private JTextArea messageText;
    private List<JPanel> bgPanel;
    private List<JLabel> bgLabel;

    private GUIClient guiClient;

    public GUI(GUIClient guiClient) {
        this.guiClient = guiClient;
        bgPanel = new ArrayList<>();
        bgLabel = new ArrayList<>();

        createMainField();
        generateScreen();

        window.setVisible(true);
    }

    public void createMainField() {
        window = new JFrame();
        // window size should be:
        // width = width of background image,
        // height = height of image + chat bar + UI
        window.setSize(1060, 863);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
//        window.setLayout(null);

        messageText = new JTextArea("----THIS IS SOME SAMPLE TEXT----");
        messageText.setBounds(50, 663, 700, 150);
        messageText.setBackground(Color.BLUE);
        messageText.setForeground(Color.WHITE);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        window.add(messageText);

    }

    /**
     * TODO: create/load all the backgrounds and labels into their containers, set required fields,
     *  add to the window for display
     */
    public void createBackground(int bgNum, String bgFileName) {

        bgPanel.add(new JPanel());

        bgPanel.get(0).setBounds(50, 50, 1060, 663);
        bgPanel.get(0).setBackground(Color.BLUE);
        bgPanel.get(0).setLayout(null);
        window.add(bgPanel.get(0));

        bgLabel.add(new JLabel());
        bgLabel.get(0).setBounds(0, 0, 1060, 663);

        // TODO: Load Image Icon with class resource loader

        ImageIcon bgIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("foyer2.jpg")));
        bgLabel.get(0).setIcon(bgIcon);
        //------------

    }

    /**
     * TODO: create objects that can be added to the background as interactive items.
     */
    public void createObject_menu(int bgNum, int objx, int objy, int objWidth, int objHeight, String objFileName,
                                  String choice1Name, String choice2Name, String choice3Name,
                                  String choice1Command, String choice2Command, String choice3Command) {

        // CREATE POPUP MENU
        JPopupMenu popupMenu = new JPopupMenu();

        // CREATE POPUP MENU ITEMS
        JMenuItem menuItem[] = new JMenuItem[4];

        menuItem[1] = new JMenuItem(choice1Name);
        menuItem[1].addActionListener(guiClient.getaHandler());
        menuItem[1].setActionCommand("1");
        popupMenu.add(menuItem[1]);

        menuItem[2] = new JMenuItem(choice2Name);
        menuItem[2].addActionListener(guiClient.getaHandler());
        menuItem[2].setActionCommand(choice2Command);
        popupMenu.add(menuItem[2]);

        menuItem[3] = new JMenuItem(choice3Name);
        menuItem[3].addActionListener(guiClient.getaHandler());
        menuItem[3].setActionCommand(choice3Command);
        popupMenu.add(menuItem[3]);

        // CREATE 1 OBJECT - Start
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, objWidth, objHeight);

        /** TODO: Load Image Icon with class resource loader
         * ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(FileName));
         * objectLabel.setIcon(objectIcon);
         * to ensure to object will show we have to layer the containers properly.
         * Object gets placed into background panel, then the background*/

        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));
        objectLabel.setIcon(objectIcon);
        // CREATE MOUSE LISTENER AND ATTACH MENU
        objectLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(objectLabel, e.getX(), e.getY());
                    // what happens after they push the button.
                }
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
        });
        bgPanel.get(0).add(objectLabel);
        bgPanel.get(0).add(bgLabel.get(0));
        //---------------------
        // - End
    }

    public void generateScreen() {
        /** SCREEN1
         * createBackground(1, FileName for background);
         * create objects can be done as many times as we need objects
         * to be located on the screen
         * createObject(1, 400, 140, 200, 200, FileName to object, "throw", "pick", "eat")*/
        createBackground(1, "foyer2.jpg");
        createObject_menu(1, 355, 55, 511, 511, "boots.png",
                "throw", "pick", "eat",
                "talkboot", "lookboot", "attackboot");
        createObject_button(2,100,200,267,73, "newGame.png");
        createObject_button(3,100,280,285,74, "loadGame.png");
        createObject_button(4,100,360,236,73, "settings.png");
        createObject_button(5,100,440,145,71, "help.png");
    }

    public JTextArea getMessageText() {
        return messageText;
    }

    public void setMessageText(JTextArea messageText) {
        this.messageText = messageText;
    }

    // TODO Henry adds button function;
    public void createObject_button(int bgNum, int objx, int objy, int objWidth, int objHeight, String objFileName) {


        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, objWidth, objHeight);
        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFileName));
        objectLabel.setIcon(objectIcon);

        JButton btn = new JButton();
        // Set button size and location.
        btn.addActionListener(guiClient.getaHandler());
        btn.setActionCommand(String.valueOf(bgNum));

//        btn.setBounds(objx, objy, objWidth, objHeight);
//        container.add(btn);
        objectLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)){
                    btn.doClick();

                }

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
        });
        bgPanel.get(0).add(objectLabel);
        bgPanel.get(0).add(bgLabel.get(0));

    }
    // TODO Henry adds button function ends;


}
