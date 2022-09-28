package com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GUI {
    private static final int NUM_OF_BGS = 10;
    private static final int NUM_OF_OBJECTS = 12;
    private GUIClient guiClient;
    private JFrame window;
    private JTextArea messageText;
    private List<JPanel> bgPanel;
    private List<JLabel> bgLabel;
    private List<JLabel> objLabel;
    private boolean musicOn = false;

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

        bgPanel.get(0).add(objLabel.get(0)); // Start button.
        bgPanel.get(0).add(objLabel.get(1)); // Story button.
        bgPanel.get(0).add(objLabel.get(2)); // Music button.
        bgPanel.get(0).add(objLabel.get(3)); // Help button.
        bgPanel.get(0).add(bgLabel.get(0)); // Main background.

        bgPanel.get(0).setVisible(true);
        window.add(bgPanel.get(0));
        window.setVisible(true);
    }

    public void restartGame() {

        // TODO reset visibility for cat and dog to true.
        for (int i = 0; i < objLabel.size(); i++) {
            objLabel.get(i).setVisible(true);
        }
        // TODO reset inventory box.
        setBox1("empty");
        setBox2("empty");
        // TODO generate the foyer screen.
        generateScreen(1);
    }

    private void createMainField() {
        // window size should be:
        // width = width of background image,
        // height = height of image + chat bar + UI
        window.setSize(1000, 873); // Henry changed the height from 863 to 913.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

        messageText.setBounds(30, 663, 935, 170); // Henry changed the height from 150 to 170. x from 50 to 30. width 900 to 935
        messageText.setBackground(Color.BLUE);
        messageText.setForeground(Color.WHITE);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        window.add(messageText);

        messageText.setVisible(false);
    }

    /**
     * TODO: create/load all the backgrounds and labels into their containers, set required fields,
     *  add to the window for display
     */
    private void initializeBackgroundPanels() {
        for (int i = 0; i < NUM_OF_BGS; i++) {
            String fileName = "bg_" + (i) + ".png";

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

        // Loop to set up first 8 objects: start-screen items and arrow directionals
        // (objectLabel: new arrow positions 0 - 7)
        for (int i = 0; i < NUM_OF_OBJECTS; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();
            String fileName = "btn_" + (i) + ".png";

            switch (i) {
                case 0:
                    objLabel.get(i).setBounds(40, 150, 160, 60);
                    btn.setActionCommand("foyer");
                    break;
                case 1:
                    objLabel.get(i).setBounds(40, 220, 160, 60);
                    btn.setActionCommand("story");
                    break;
                case 2:
                    objLabel.get(i).setBounds(40, 290, 170, 60);
                    btn.setActionCommand("music player");
                    break;
                case 3:
                    objLabel.get(i).setBounds(40, 360, 150, 60);
                    btn.setActionCommand("help");
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
                case 8: // Start over, all room pages.
                    objLabel.get(i).setBounds(790, 10, 220, 65);
                    btn.setActionCommand("new game");
                    break;
                case 9: // Start a new game, win/lose page.
                    objLabel.get(i).setBounds(15, 125, 406, 100);
                    btn.setActionCommand("new game");
                    break;
                case 10:// Quit, all page.
                    objLabel.get(i).setBounds(890, 60, 111, 65);
                    btn.setActionCommand("quit");
                    break;
                case 11:// Quit, win/lose page.
                    objLabel.get(i).setBounds(35, 220, 212, 106);
                    btn.setActionCommand("quit");
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


        // Loop to set up last 4 arrow objects that are needed for the rooms
        // (objectLabel: new arrow positions 8 - 11)
        for (int i = 12; i < 16; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();

            // TODO: GARAGE and LOUNGE mapped to the same button and they both get called in the loft. fix
            switch (i) {
                case 12:
                    objLabel.get(i).setBounds(7, 320, 100, 71);
                    btn.setActionCommand("bathroom");
                    objLabel.get(i).setIcon(objLabel.get(4).getIcon());
                    break;
                case 13:
                    objLabel.get(i).setBounds(892, 320, 100, 71);
                    btn.setActionCommand("closet");
                    objLabel.get(i).setIcon(objLabel.get(5).getIcon());
                    break;
                case 14:
                    objLabel.get(i).setBounds(457, 7, 71, 100);
                    btn.setActionCommand("garden");
                    objLabel.get(i).setIcon(objLabel.get(6).getIcon());
                    break;
                case 15:
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
        for (int i = 16; i < 18; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();
            String fileName = "ch_" + (i - 16) + ".png";
            switch (i) {
                case 16:
                    objLabel.get(i).setBounds(270, 480, 200, 149);
                    btn.setActionCommand("dog");
                    break;
                case 17:
                    objLabel.get(i).setBounds(250, 350, 200, 278);
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
        for (int i = 18; i < 25; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();
            String fileName = "obj_" + (i - 18) + ".png";

            switch (i) {
                case 18:
                    objLabel.get(i).setBounds(830, 250, 90, 140);
                    btn.setActionCommand("drumstick");
                    break;
                case 19:
                    objLabel.get(i).setBounds(830, 400, 100, 40);
                    btn.setActionCommand("cucumber");
                    break;
                case 20:
                    objLabel.get(i).setBounds(520, 417, 100, 100);
                    btn.setActionCommand("shoes");
                    break;
                case 21:
                    objLabel.get(i).setBounds(220, 280, 100, 100);
                    btn.setActionCommand("wool");
                    break;
                case 22:
                    objLabel.get(i).setBounds(750, 320, 100, 70);
                    btn.setActionCommand("key");
                    break;
                case 23:
                    objLabel.get(i).setBounds(770, 535, 100, 140);
                    btn.setActionCommand("box1");
                    break;
                case 24:
                    objLabel.get(i).setBounds(875, 535, 100, 140);
                    btn.setActionCommand("box2");
                    break;

            }
            ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            objLabel.get(i).setIcon(objectIcon);

            btn.addActionListener(guiClient.getaHandler());
            objLabel.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    playSE(6);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        playSE(6);
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

        //TODO- for loop for btns 25-27

        for (int i = 25; i < 28; i++) {
            objLabel.add(new JLabel());
            JButton btn = new JButton();
            String fileName = "btn_" + (i) + ".png";

            switch (i) {
                case 25:// Volume Up
                    objLabel.get(i).setBounds(35, 10, 64, 64);
                    btn.setActionCommand("volume up");
                    break;
                case 26:// Volume Down
                    objLabel.get(i).setBounds(110, 10, 64, 64);
                    btn.setActionCommand("volume down");
                    break;
                case 27:// Mute
                    objLabel.get(i).setBounds(185, 10, 64, 64);
                    btn.setActionCommand("mute");
                    break;
            }

            //TODO FIX THIS
            ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
            objLabel.get(i).setIcon(objectIcon);

            btn.addActionListener(guiClient.getaHandler());
            objLabel.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //playSE(6);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        // playSE(6);
                        //System.out.println("just testing btns");
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
                bgPanel.get(bgNum).add(objLabel.get(4)); // L Btn to Lounge.
                bgPanel.get(bgNum).add(objLabel.get(5)); // R Btn to Kitchen.
                bgPanel.get(bgNum).add(objLabel.get(23)); // Inventory box 1.
                bgPanel.get(bgNum).add(objLabel.get(24)); // Inventory box 2.
                bgPanel.get(bgNum).add(objLabel.get(8)); // Start over.
                bgPanel.get(bgNum).add(objLabel.get(10)); // Quit
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }

                break;
            case 2:
                bgPanel.get(bgNum).add(objLabel.get(4)); // L Btn to Lounge.
                bgPanel.get(bgNum).add(objLabel.get(6)); // U Btn to Foyer.
                bgPanel.get(bgNum).add(objLabel.get(7)); // D Btn to Loft.
                bgPanel.get(bgNum).add(objLabel.get(18)); // Drumstick.
                bgPanel.get(bgNum).add(objLabel.get(19)); // Cucumber.
                bgPanel.get(bgNum).add(objLabel.get(23)); // Inventory box 1.
                bgPanel.get(bgNum).add(objLabel.get(24)); // Inventory box 2.
                bgPanel.get(bgNum).add(objLabel.get(8)); // Start over.
                bgPanel.get(bgNum).add(objLabel.get(10)); // Quit
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }
                break;
            case 3:
                // mix between directional arrows 1-4 and 12-15

                bgPanel.get(bgNum).add(objLabel.get(16)); // Dog button.
                objLabel.get(16).setVisible(true);
                objLabel.get(4).setVisible(false); // Don't show arrows before dog is distracted.
                objLabel.get(5).setVisible(false); // Don't show arrows before dog is distracted.
                objLabel.get(15).setVisible(false); // Don't show arrows before dog is distracted.
                bgPanel.get(bgNum).add(objLabel.get(23)); // Inventory box 1.
                bgPanel.get(bgNum).add(objLabel.get(24)); // Inventory box 2.
                bgPanel.get(bgNum).add(objLabel.get(8)); // Start over.
                bgPanel.get(bgNum).add(objLabel.get(10)); // Quit
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }
                break;
            case 4:
                bgPanel.get(bgNum).add(objLabel.get(14)); // Btn to garden.
                bgPanel.get(bgNum).add(objLabel.get(7)); // D Btn to Loft.
                bgPanel.get(bgNum).add(objLabel.get(22)); // Key.
                bgPanel.get(bgNum).add(objLabel.get(23)); // Inventory box 1.
                bgPanel.get(bgNum).add(objLabel.get(24)); // Inventory box 2.
                bgPanel.get(bgNum).add(objLabel.get(8)); // Start over.
                bgPanel.get(bgNum).add(objLabel.get(10)); // Quit
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }
                break;
            case 5:
                bgPanel.get(bgNum).add(objLabel.get(17)); // Cat button.
                objLabel.get(17).setVisible(true);
                objLabel.get(5).setVisible(false); // Don't show arrows before cat is distracted.
                objLabel.get(6).setVisible(false); // Don't show arrows before cat is distracted.
                objLabel.get(7).setVisible(false); // Don't show arrows before cat is distracted.
                objLabel.get(12).setVisible(false); // Don't show arrows before cat is distracted.
                bgPanel.get(bgNum).add(objLabel.get(23)); // Inventory box 1.
                bgPanel.get(bgNum).add(objLabel.get(24)); // Inventory box 2.
                bgPanel.get(bgNum).add(objLabel.get(8)); // Start over.
                bgPanel.get(bgNum).add(objLabel.get(10)); // Quit
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }
                break;
            case 6:
                bgPanel.get(bgNum).add(objLabel.get(4)); // L Btn to Lounge.
                bgPanel.get(bgNum).add(objLabel.get(13)); // Closet.
                bgPanel.get(bgNum).add(objLabel.get(23)); // Inventory box 1.
                bgPanel.get(bgNum).add(objLabel.get(24)); // Inventory box 2.
                bgPanel.get(bgNum).add(objLabel.get(8)); // Start over.
                bgPanel.get(bgNum).add(objLabel.get(10)); // Quit
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }
                break;
            case 7:
                bgPanel.get(bgNum).add(objLabel.get(12)); // Btn to Bathroom.
                bgPanel.get(bgNum).add(objLabel.get(20)); // Shoes.
                bgPanel.get(bgNum).add(objLabel.get(21)); // Wools.
                bgPanel.get(bgNum).add(objLabel.get(23)); // Inventory box 1.
                bgPanel.get(bgNum).add(objLabel.get(24)); // Inventory box 2.
                bgPanel.get(bgNum).add(objLabel.get(8)); // Start over.
                bgPanel.get(bgNum).add(objLabel.get(10)); // Quit
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }
                break;
            case 8:
            case 9:
                bgPanel.get(bgNum).add(objLabel.get(9)); // New Game?
                bgPanel.get(bgNum).add(objLabel.get(11)); // Quit.
                if (musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25));//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26));//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27));//Mute
                } else if (!musicOn) {
                    bgPanel.get(bgNum).add(objLabel.get(25)).setVisible(false);//Volume Up
                    bgPanel.get(bgNum).add(objLabel.get(26)).setVisible(false);//Volume Down
                    bgPanel.get(bgNum).add(objLabel.get(27)).setVisible(false);//Mute
                }
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


    public void playMusic(int i) {
        guiClient.getSound().setFile(i);
        guiClient.getSound().play();
        guiClient.getSound().loop();
    }

    public void stopMusic() {
        guiClient.getSound().stop();
    }

    public void playSE(int i) { // for Sound Effects
        guiClient.getSound().setFile(i);
        guiClient.getSound().play();
    }

    public void volumeUpGUI() { // for Sound Effects
        guiClient.getSound().volumeUp();
    }

    public void volumeDownGUI() { // for Sound Effects
        guiClient.getSound().volumeDown();
    }

    public void volumeMuteGUI() {
        guiClient.getSound().volumeMute();
        System.out.println("volume should be muted " + guiClient.getSound().isMute());
        /*if(guiClient.getSound().isMute()) {
          guiClient.getSound().volumeMute(false);
        } else {
            guiClient.getSound().volumeMute(true);
        }*/
    }


    // TODO Henry added this function to enable the navigation arrows after npc is distracted.
    public void generateNpcScreen(int bgNum, String npc) {

        messageText.setVisible(true);
        for (JPanel panel : bgPanel) {
            panel.setVisible(false);

            if (npc.equals("dog")) {
                bgPanel.get(bgNum).add(objLabel.get(4)); // L Btn to Lounge.
                objLabel.get(4).setVisible(true);
                bgPanel.get(bgNum).add(objLabel.get(5)); // R Btn to Kitchen.
                objLabel.get(5).setVisible(true);
                bgPanel.get(bgNum).add(objLabel.get(15)); // Btn to garage.
                objLabel.get(15).setVisible(true);
                objLabel.get(16).setVisible(false); // Remove dog.
            } else if (npc.equals("cat")) {
                bgPanel.get(bgNum).add(objLabel.get(5)); // R Btn to Kitchen.
                objLabel.get(5).setVisible(true);
                bgPanel.get(bgNum).add(objLabel.get(7)); // D Btn to Loft.
                objLabel.get(7).setVisible(true);
                bgPanel.get(bgNum).add(objLabel.get(6)); // U Btn to Foyer.
                objLabel.get(6).setVisible(true);
                bgPanel.get(bgNum).add(objLabel.get(12));  // To bathroom.
                objLabel.get(12).setVisible(true);
                objLabel.get(17).setVisible(false);
                ; // Remove cat.
            }
        }
        bgPanel.get(bgNum).add(bgLabel.get(bgNum));
        bgPanel.get(bgNum).setVisible(true);
        window.add(bgPanel.get(bgNum));
    }

    // TODO Henry try to add function to remove the item after added to inventory and make them stay at each page.
    public void setBox1(String itemName) {
        // TODO change icon picture.
        // itemName is actionValue.
        List<String> items = new ArrayList<>(Arrays.asList("drumstick", "cucumber", "shoes", "wool", "key", "empty"));

        String fileName = "obj_" + items.indexOf(itemName) + ".png";
        ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
        objLabel.get(23).setIcon(objectIcon);

    }

    public void setBox2(String itemName) {
        // TODO change icon picture.
        // itemName is actionValue.
        List<String> items = new ArrayList<>(Arrays.asList("drumstick", "cucumber", "shoes", "wool", "key", "empty"));

        String fileName = "obj_" + items.indexOf(itemName) + ".png";
        ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
        objLabel.get(24).setIcon(objectIcon);
    }

    public void removeObj(String itemName) { // remove the Obj from the room after added to inventory.
        // 14 - 18
        // "drumstick", "cucumber", "shoes", "wool", "key"
        if (itemName.equals("drumstick")) {
            playSE(4);
            bgPanel.get(2).add(objLabel.get(18)).setVisible(false);
        } else if (itemName.equals("cucumber")) {
            bgPanel.get(2).add(objLabel.get(19)).setVisible(false);
        } else if (itemName.equals("shoes")) {
            bgPanel.get(7).add(objLabel.get(20)).setVisible(false);
        } else if (itemName.equals("wool")) {
            bgPanel.get(7).add(objLabel.get(21)).setVisible(false);
        } else if (itemName.equals("key")) {
            playSE(5);
            bgPanel.get(4).add(objLabel.get(22)).setVisible(false);
        }
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

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

}
