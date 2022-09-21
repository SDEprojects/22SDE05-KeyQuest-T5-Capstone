package com.gui;

import com.game.model.Character;
import com.game.model.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.game.utility.JSONParser.getStartingRoom;

public class GUI {
    private static final int NUM_OF_BGS = 9;
    private static final int NUM_OF_OBJECTS = 8;
    private GUIClient guiClient;
    private JFrame window;
    private JTextArea messageText;
    private List<JPanel> bgPanel;
    private List<JLabel> bgLabel;
    private List<JLabel> objLabel;

    public GUI(GUIClient guiClient) {
        this.guiClient = guiClient;
        bgPanel = new ArrayList<>();
        bgLabel = new ArrayList<>();
        objLabel = new ArrayList<>();
        window = new JFrame();
        messageText = new JTextArea("--- SOMETHING TO SAY ---");

        createMainField();
        initializeBackgroundPanels();
        initializeBackgroundObjects();

        bgPanel.get(0).add(objLabel.get(0));
        bgPanel.get(0).add(objLabel.get(1));
        bgPanel.get(0).add(objLabel.get(2));
        bgPanel.get(0).add(objLabel.get(3));

        bgPanel.get(0).add(bgLabel.get(0));

        window.add(bgPanel.get(0));
        window.setVisible(true);
    }

    private void createMainField() {
        // window size should be:
        // width = width of background image,
        // height = height of image + chat bar + UI
        window.setSize(1000, 863);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

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
        for (int i = 0; i < NUM_OF_BGS; i++){
            String fileName = "bg_" + (i + 1) + ".png";

            bgPanel.add(new JPanel());
            bgPanel.get(i).setBounds(0, 0, 1000, 700);
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

        for(int i = 0; i < NUM_OF_OBJECTS; i++){
            objLabel.add(new JLabel());
            String fileName = "btn_" + (i + 1) + ".png";

            switch(i){
                case 0:
                    objLabel.get(i).setBounds(15, 150, 350, 100);
                    break;
                case 1:
                    objLabel.get(i).setBounds(15, 220, 325, 100);
                    break;
                case 2:
                    objLabel.get(i).setBounds(15, 290, 300, 100);
                    break;
                case 3:
                    objLabel.get(i).setBounds(15, 360, 275, 100);
                    break;
                case 4:
                    objLabel.get(i).setBounds(1, 1, 250, 225);
                    break;
                case 5:
                    objLabel.get(i).setBounds(1, 1, 225, 250);
                    break;
                case 6:
                    objLabel.get(i).setBounds(1, 1, 200, 275);
                    break;
                case 7:
                    objLabel.get(i).setBounds(1, 1, 175, 300);
                    break;
            }
            ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            objLabel.get(i).setIcon(objectIcon);

            JButton btn = new JButton();
            // Set button size and location.
            btn.addActionListener(guiClient.getaHandler());
            btn.setActionCommand("new game");
            objLabel.get(i).addMouseListener(new MouseListener() {
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
        }
    }

    public void generateScreen(int bgNum){

        // TODO: ADD THIS BACK SOMEWHERE
        // Adds the background panel to the window as though it's a list.
        // window.add(bgPanel.get(0));
        System.out.println("DID WE GET HERE");
        //createMainField();
        bgPanel.get(bgNum).add(objLabel.get(4));
        bgPanel.get(bgNum).add(objLabel.get(5));
        bgPanel.get(bgNum).add(objLabel.get(6));
        bgPanel.get(bgNum).add(objLabel.get(7));

        bgPanel.get(bgNum).add(bgLabel.get(0));
        /*for (JPanel panel: bgPanel) {
            panel.setVisible(false);
        }*/

        bgPanel.get(bgNum).setVisible(true);
        window.add(bgPanel.get(bgNum));
        //window.setVisible(true);
    }

    /**
     * TODO: create objects that can be added to the background as interactive items.
     */
    public void createMenu() {

        // CREATE POPUP MENU
        JPopupMenu popupMenu = new JPopupMenu();

        // CREATE POPUP MENU ITEMS
        JMenuItem menuItem[] = new JMenuItem[4];

        menuItem[1] = new JMenuItem();
        menuItem[1].addActionListener(guiClient.getaHandler());
        menuItem[1].setActionCommand("testing");
        popupMenu.add(menuItem[1]);

        menuItem[2] = new JMenuItem();
        menuItem[2].addActionListener(guiClient.getaHandler());
        menuItem[2].setActionCommand("test");
        popupMenu.add(menuItem[2]);

        menuItem[3] = new JMenuItem();
        menuItem[3].addActionListener(guiClient.getaHandler());
        menuItem[3].setActionCommand("testinging");
        popupMenu.add(menuItem[3]);

    }

    public JTextArea getMessageText() {
        return messageText;
    }

    public void setMessageText(JTextArea messageText) {
        this.messageText = messageText;
    }

    public void setupRoom(){
        Character cat = new Character("cat");
        Character dog = new Character("dog");
        Location location = new Location(getStartingRoom());
        String[] listNextLocations = location.getDirections();
        String[] characters = location.getCharacter();
        if (characters.length != 0) {
            if (Arrays.asList(characters).contains("cat")) {
                getMessageText().setText("Current location is " + getStartingRoom() +
                        "\n" + location.getDescription() + "\n" + cat.getDescription()+
                        "\nLooks like there are some items here: " + Arrays.toString(location.getItems()));
            } else if (Arrays.asList(characters).contains("dog")) {
                getMessageText().setText("Current location is " + getStartingRoom() +
                        "\n" + location.getDescription() + "\n" + dog.getDescription()+
                        "\nLooks like there are some items here: " + Arrays.toString(location.getItems()));
            }
        } else {
            getMessageText().setText("Current location is " + getStartingRoom() +
                    "\n" + location.getDescription() +
             "\nLooks like there are some items here: " + Arrays.toString(location.getItems()));
        }
    }
    // TODO Henry adds button function ends;


    public List<JPanel> getBgPanel() {
        return bgPanel;
    }

    public void setBgPanel(List<JPanel> bgPanel) {
        this.bgPanel = bgPanel;
    }

    public List<JLabel> getBgLabel() {
        return bgLabel;
    }

    public void setBgLabel(List<JLabel> bgLabel) {
        this.bgLabel = bgLabel;
    }

    public List<JLabel> getObjLabel() {
        return objLabel;
    }

    public void setObjLabel(List<JLabel> objLabel) {
        this.objLabel = objLabel;
    }
}
