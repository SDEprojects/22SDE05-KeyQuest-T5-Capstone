package com.gui;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class mainPage {
    private java.util.List<JPanel> bgPanel;
    private List<JLabel> bgLabel;
    private JFrame window;
    private JTextArea messageText;

    public mainPage() {
        bgPanel = new ArrayList<>();
        bgLabel = new ArrayList<>();

        page1();
        page2();

        window.setVisible(true);

    }
    public void page1() {
        window = new JFrame();
        // window size should be:
        // width = width of background image,
        // height = height of image + chat bar + UI
        window.setSize(1200, 927);
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
    public void page2() {

        bgPanel.add(new JPanel());
        bgPanel.get(0).setBounds(50, 50, 1200, 927);
        bgPanel.get(0).setBackground(Color.BLUE);
        bgPanel.get(0).setLayout(null);
        window.add(bgPanel.get(0));

        bgLabel.add(new JLabel());
        bgLabel.get(0).setBounds(0, 0, 1200, 927);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("foyer2.jpg"));
        bgLabel.get(0).setIcon(bgIcon);
    }

    public void page3(){

    }

}
