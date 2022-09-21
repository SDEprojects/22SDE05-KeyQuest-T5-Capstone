package com.gui;

import com.game.controller.ActionHandler;
import com.game.controller.EventHandler;

public class GUIClient{

    private ActionHandler aHandler = new ActionHandler(this);

    private GUI gui = new GUI(this);

    public static void main(String[] args) throws InterruptedException {
        new GUIClient();

    }

    public GUIClient(){
    }

    public ActionHandler getaHandler() {
        return aHandler;
    }

    public void setaHandler(ActionHandler aHandler) {
        this.aHandler = aHandler;
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }
}