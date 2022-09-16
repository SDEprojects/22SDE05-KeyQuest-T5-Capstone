package com.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private JFrame window;
    private JTextArea messageText;
    private List<JPanel> bgPanel;
    private List<JLabel> bgLabel;

    public GUI(){
        bgPanel = new ArrayList<>();
        bgLabel = new ArrayList<>();

        createMainField();
        createBackground();

        window.setVisible(true);
    }

    public void createMainField(){
        window = new JFrame();
        // window size should be:
        // width = width of background image,
        // height = height of image + chat bar + UI
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
//        window.setLayout(null);

        messageText = new JTextArea("----THIS IS SOME SAMPLE TEXT----");
        messageText.setBounds(50, 400, 700, 150);
        messageText.setBackground(Color.BLUE);
        messageText.setForeground(Color.WHITE);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        window.add(messageText);
        // TODO Henry add the following code to test background picture.
        window.setLayout(new BorderLayout());
        JLabel background=new JLabel(new ImageIcon("/GUI/pages/intro.jpg"));
        window.add(background);
        background.setLayout(new FlowLayout());
//        l1=new JLabel("Here is a button");
//        b1=new JButton("I am a button");
//        background.add(l1);
//        background.add(b1);
        // TODO Henry background code ends here.
    }

    /**
     * TODO: create/load all the backgrounds and labels into their containers, set required fields,
     *  add to the window for display
     */
    public void createBackground(){

    }

    /**
     * TODO: create objects that can be added to the background as interactive items.
     */
    public void createObject(){

    }
}
