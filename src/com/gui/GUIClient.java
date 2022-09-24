package com.gui;

import com.game.controller.ActionHandler;
import com.game.controller.EventHandler;

public class GUIClient{

    private ActionHandler aHandler = new ActionHandler(this);

    private EventHandler eHandler = new EventHandler(this);

    private GUI gui = new GUI(this);

    public static void main(String[] args){
        new GUIClient();
    }

    public GUIClient(){
        // intro to screen 1
        // dialog to help player
        // tutorial
    }

    public ActionHandler getaHandler() {
        return aHandler;
    }

    public void setaHandler(ActionHandler aHandler) {
        this.aHandler = aHandler;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public GUI getGui() {
        return gui;
    }

    public EventHandler geteHandler() {
        return eHandler;
    }

    public void seteHandler(EventHandler eHandler) {
        this.eHandler = eHandler;
    }

}