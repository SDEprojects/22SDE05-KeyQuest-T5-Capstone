package com.game.controller;
import com.gui.GUI;
import com.gui.GUIClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener{
    private GUIClient guiClient;
    public ActionHandler(GUIClient guiClient){
        this.guiClient = guiClient;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = e.getActionCommand();

        switch(selection){
            case "talkboot":
                guiClient.getGui().getMessageText().setText("Why are you talking to your boots");
                break;
            case "lookboot":
                break;
            case "attackboot":
                break;
        }
    }
}
