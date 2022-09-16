package com.gui;

import com.game.controller.ActionHandler;

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

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public GUI getGui() {
        return gui;
    }
}