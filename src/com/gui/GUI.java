package com.gui;

import com.game.model.Character;
import com.game.model.Location;
import com.sound.Sound;

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
    private static final int NUM_OF_BGS = 10;
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

        bgPanel.get(0).setVisible(true);
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

        messageText.setVisible(false);

        //TODO uncheck this before launch
        //playMusic(0);
    }

    /**
     * TODO: create/load all the backgrounds and labels into their containers, set required fields,
     *  add to the window for display
     */
    private void initializeBackgroundPanels() {
        for (int i = 0; i < NUM_OF_BGS; i++) {
            String fileName = "bg_" + (i + 1) + ".png";

            bgPanel.add(new JPanel());
            bgPanel.get(i).setBounds(0, 0, 1000, 663);
            bgPanel.get(i).setBackground(Color.BLUE);
            bgPanel.get(i).setLayout(null);
            bgPanel.get(i).setVisible(false);

            //--------------------------------
            bgLabel.add(new JLabel());
            bgLabel.get(i).setBounds(0, 0, 1060, 663);

            ImageIcon bgIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            bgLabel.get(i).setIcon(bgIcon);

        }

        // TODO: ADD THIS BACK SOMEWHERE
        // Adds the background panel to the window as though it's a list.
        // window.add(bgPanel.get(0));
    }

    private void initializeBackgroundObjects() {

        // Loop to setup first 8 objects: startscreen items and arrow directionals
        // (objectLabel: new arrow positions 0 - 7)
        for (int i = 0; i < NUM_OF_OBJECTS; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();
            String fileName = "btn_" + (i + 1) + ".png";

            switch (i) {
                case 0:
                    objLabel.get(i).setBounds(7, 150, 350, 100);
                    btn.setActionCommand("foyer");
                    break;
                case 1:
                    objLabel.get(i).setBounds(7, 220, 325, 100);
                    btn.setActionCommand("settings");
                    break;
                case 2:
                    objLabel.get(i).setBounds(7, 290, 400, 100);
                    btn.setActionCommand("load game");
                    break;
                case 3:
                    objLabel.get(i).setBounds(7, 360, 275, 100);
                    btn.setActionCommand("music player");
                    break;
                case 4:
                    objLabel.get(i).setBounds(7, 320, 100, 71);
                    btn.setActionCommand("lounge");
                    break;
                case 5:
                    objLabel.get(i).setBounds(892, 320, 100, 71);
                    btn.setActionCommand("kitchen");
                    break;
                case 6:
                    objLabel.get(i).setBounds(457, 7, 71, 100);
                    btn.setActionCommand("foyer");
                    break;
                case 7:
                    objLabel.get(i).setBounds(457, 552, 71, 100);
                    btn.setActionCommand("loft");
                    break;
            }


            ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            objLabel.get(i).setIcon(objectIcon);

            // Set button size and location.
            btn.addActionListener(guiClient.getaHandler());
            // TODO: upon creation, ensure the action command give the right value to each object.
            // move up into the switch case
            objLabel.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
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
        }


        // Loop to setup last 4 arrow objects that are needed for the rooms
        // (objectLabel: new arrow positions 8 - 11)
        for (int i = 8; i < 12; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();

            // TODO: GARAGE and LOUNGE mapped to the same button and they both get called in the loft. fix
            switch (i) {
                case 8:
                    objLabel.get(i).setBounds(7, 320, 100, 71);
                    btn.setActionCommand("bathroom");
                    objLabel.get(i).setIcon(objLabel.get(4).getIcon());
                    break;
                case 9:
                    objLabel.get(i).setBounds(892, 320, 100, 71);
                    btn.setActionCommand("closet");
                    objLabel.get(i).setIcon(objLabel.get(5).getIcon());
                    break;
                case 10:
                    objLabel.get(i).setBounds(457, 7, 71, 100);
                    btn.setActionCommand("garden");
                    objLabel.get(i).setIcon(objLabel.get(6).getIcon());
                    break;
                case 11:
                    objLabel.get(i).setBounds(457, 552, 71, 100);
                    btn.setActionCommand("garage");
                    objLabel.get(i).setIcon(objLabel.get(7).getIcon());
                    break;
            }

            btn.addActionListener(guiClient.getaHandler());
            objLabel.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
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
        }

        // TODO: Setup objects that interact with the player in the game
        // loop to set up the cat and dog objects (objectLabel: cat and dog are in position 12 and 13)
        for (int i = 12; i < 14; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();
            String fileName = "ch_" + (i - 11) + ".png";
            switch (i) {
                case 12:
                    objLabel.get(i).setBounds(0, 0, 600, 700);
                    btn.setActionCommand("dog");
                    break;
                case 13:
                    objLabel.get(i).setBounds(0, 0, 600, 700);
                    btn.setActionCommand("cat");
                    break;
            }
            ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            objLabel.get(i).setIcon(objectIcon);

            btn.addActionListener(guiClient.getaHandler());
            objLabel.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
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
        }

        // Loop to setup the food objects in the game (objectlabel: items object positions 14 - 18)
        for(int i = 14; i < 19; i++){
            objLabel.add(new JLabel());
            JButton btn = new JButton();
            String fileName = "obj_" + (i - 13) + ".png";

            switch (i) {
                case 14:
                    objLabel.get(i).setBounds(7, 150, 350, 100);
                    btn.setActionCommand("drumstick");
                    break;
                case 15:
                    objLabel.get(i).setBounds(7, 220, 325, 100);
                    btn.setActionCommand("cucumber");
                    break;
                case 16:
                    objLabel.get(i).setBounds(7, 290, 400, 100);
                    btn.setActionCommand("shoes");
                    break;
                case 17:
                    objLabel.get(i).setBounds(10, 290, 400, 100);
                    btn.setActionCommand("wool");
                    break;
                case 18:
                    objLabel.get(i).setBounds(20, 290, 400, 100);
                    btn.setActionCommand("key");
                    break;
            }
            ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            objLabel.get(i).setIcon(objectIcon);

            btn.addActionListener(guiClient.getaHandler());
            objLabel.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
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
        }
    }


    public void generateScreen(int bgNum) {

        messageText.setVisible(true);
        for (JPanel panel : bgPanel) {
            panel.setVisible(false);
        }

        switch (bgNum) {
            case 1:
                bgPanel.get(bgNum).add(objLabel.get(4));
                bgPanel.get(bgNum).add(objLabel.get(5));
                break;
            case 2:
                bgPanel.get(bgNum).add(objLabel.get(4));
                bgPanel.get(bgNum).add(objLabel.get(6));
                bgPanel.get(bgNum).add(objLabel.get(7));
                bgPanel.get(bgNum).add(objLabel.get(14));
                bgPanel.get(bgNum).add(objLabel.get(15));
                break;
            case 3:
                // mix between directional arrows 1-4 and 8-11
                bgPanel.get(bgNum).add(objLabel.get(4));
                bgPanel.get(bgNum).add(objLabel.get(5));
                bgPanel.get(bgNum).add(objLabel.get(11));
                bgPanel.get(bgNum).add(objLabel.get(12));
                break;
            case 4:
                bgPanel.get(bgNum).add(objLabel.get(10));
                bgPanel.get(bgNum).add(objLabel.get(7));
                bgPanel.get(bgNum).add(objLabel.get(18));
                break;
            case 5:
                bgPanel.get(bgNum).add(objLabel.get(5));
                bgPanel.get(bgNum).add(objLabel.get(7));
                bgPanel.get(bgNum).add(objLabel.get(8));
                bgPanel.get(bgNum).add(objLabel.get(13));
                break;
            case 6:
                bgPanel.get(bgNum).add(objLabel.get(4));
                bgPanel.get(bgNum).add(objLabel.get(9));
                break;
            case 7:
                bgPanel.get(bgNum).add(objLabel.get(8));
                bgPanel.get(bgNum).add(objLabel.get(16));
                bgPanel.get(bgNum).add(objLabel.get(17));
                break;
        }

        // TODO: new action command values for the new arrow buttons, and any other object that's being created.

        bgPanel.get(bgNum).add(bgLabel.get(bgNum));

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

    Sound sound = new Sound();

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    //TODO add sound effects
    public void playSE(int i) { // for Sound Effects
        sound.setFile(i);
        sound.play();
    }

    public JTextArea getMessageText() {
        return messageText;
    }

    public void setMessageText(JTextArea messageText) {
        this.messageText = messageText;
    }

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
