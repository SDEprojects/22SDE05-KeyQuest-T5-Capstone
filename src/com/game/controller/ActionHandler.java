package com.game.controller;
import com.game.utility.Room;
import com.gui.GUIClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener{
    private GUIClient guiClient;

    private EventHandler eHandler;
    public ActionHandler(GUIClient guiClient){
        this.guiClient = guiClient;
        this.eHandler = new EventHandler(this.guiClient);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selection = e.getActionCommand();
        //System.out.println(e.getSource().getClass().getCanonicalName()); //javax.swing.JButton
        eHandler.eventRequest(Room.KITCHEN.toString().toLowerCase(), selection);
        // eManager.actionRequest(Map.LOUNGE, selection); --> public boolean actionRequest(String request)
        // possible event triggers: sub-menu will do a text response.
    }

    public void requestedAction_button(){

    }


}
