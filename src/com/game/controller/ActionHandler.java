package com.game.controller;
import com.gui.GUIClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener{
    private GUIClient guiClient;

    // private EventManager eManager;
    public ActionHandler(GUIClient guiClient){
        this.guiClient = guiClient;
        // eManager = new EventManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = e.getActionCommand();
        System.out.println(e.getSource().getClass().getCanonicalName()); //javax.swing.JButton

        // String response = eManager.actionRequest(e.getActionCommand()); --> public boolean actionRequest(String request)
        // eManager.actionResponse(response); --> public void actionResponse(String response){if(response){do something...}}
        // possible event triggers: sub-menu will do a text response.
        switch(selection){
            case "1":
                guiClient.getGui().getMessageText().setText("Why are you talking to your boots");
                break;
            case "lookboot":
                break;
            case "attackboot":
                break;
        }

        switch(e.getActionCommand()){
            case "1": requestedAction_button();
        }
    }

    public void requestedAction_button(){

    }
}
