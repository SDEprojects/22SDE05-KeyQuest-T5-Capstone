package com.gui;

public class FunctionTest {
    public static void main(String[] args) {
//        mainPage m = new mainPage();
//        m.page1();
//        m.page2();
        GUI gui = new GUI(new GUIClient());

//        gui.createObject_button("New Game", 150, 150, 200, 50);
//        gui.createObject_button("Load Game", 150, 250, 200, 50);
//        gui.createObject_button("Settings", 150, 350, 200, 50);
//        gui.createObject_button("Help", 150, 450, 200, 50);
        gui.createBackground(1, "Test");
        gui.generateScreen();

    }
}
