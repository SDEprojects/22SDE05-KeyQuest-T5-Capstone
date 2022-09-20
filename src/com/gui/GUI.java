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

    private void createMainField() {
        window = new JFrame();
        // window size should be:
        // width = width of background image,
        // height = height of image + chat bar + UI
        window.setSize(1060, 863);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

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
    private void initializeBackgroundPanels() {
        for (int i = 0; i < NUM_OF_SCREENS; i++){
            String fileName = "background_" + i + ".jpg";

            bgPanel.add(new JPanel());
            bgPanel.get(i).setBounds(50, 50, 1060, 663);
            bgPanel.get(i).setBackground(Color.BLUE);
            bgPanel.get(i).setLayout(null);

            bgLabel.add(new JLabel());
            bgLabel.get(i).setBounds(0, 0, 1060, 663);

            ImageIcon bgIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            bgLabel.get(i).setIcon(bgIcon);
        }

        // TODO: ADD THIS BACK SOMEWHERE
        // Adds the background panel to the window as though it's a list.
        // window.add(bgPanel.get(0));
    }

    private void initializeBackgroundObjects(){
        /**
         * TODO: Create objects for each background
         * Foyer
         * 1. Kitchen Directional
         * 2. Living room Directional
         * 3. Go back or Exit game Directional
         * Kitchen
         * 4. Loft Directional
         * 5. Foyer Directional
         * 6. Living room Directional
         * 7. Food Item
         * 8. Exit Game Directional?
         * Living Room
         * 9. Cat Item
         * 10. Foyer Directional
         * 11. Kitchen Directional
         * 12. Loft Directional
         * 13. Bathroom Directional
         * Bathroom
         * 14. Walk-in Closet Directional
         * 15. Living room directional
         * Walk-in Closet
         * 16. Dog Item
         * 17. Cat Item
         * 18. Food Item
         * 19. Bathroom Directional
         * Garage
         * 20. Key Item
         * 21. Loft Directional
         * 22. Garden Directional
         * Loft
         * 23. Dog Item
         * 24. Living Room Directional
         * 25. Garage Directional
         * Garden
         * Actual Objects to build
         * 1. left Directional
         * 2. right Directional
         * 3. back directional
         * 4. up directional
         * 6. Food Item
         * 7. Food Item # 2
         * 8. Dog Item
         * 9. Cat Item
         * 14. Key Item
         * 15. Boot Item
         */
    }

    private void generateStartScreen(){
        // TODO: ADD THIS BACK SOMEWHERE
        // Adds the background panel to the window as though it's a list.
        // window.add(bgPanel.get(0));
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
        menuItem[1].setActionCommand(choice1Command);
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
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    popupMenu.show(objectLabel, e.getX(), e.getY());
                    // what happens after they push the button.
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        // TODO: ADD THIS BACK SOMEWHERE
        // bgPanel.get(0).add(objectLabel);
        // bgPanel.get(0).add(bgLabel.get(0));
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
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)){
                    btn.doClick();

                }

            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        bgPanel.get(0).add(objectLabel);
        bgPanel.get(0).add(bgLabel.get(0));

    }
    // TODO Henry adds button function ends;


}
