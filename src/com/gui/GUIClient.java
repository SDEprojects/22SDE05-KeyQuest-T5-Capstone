package com.gui;

import com.game.controller.ActionHandler;
import com.game.controller.EventHandler;

public class GUIClient{

    private ActionHandler aHandler = new ActionHandler(this);

    private EventHandler eHandler = new EventHandler(this);

    private GUI gui = new GUI(this);

    public static void main(String[] args) throws InterruptedException {
        new GUIClient();
        // TODO Go get some eye drops, Henry adds some code here, again.


        // TODO need to fix the gui call.
//        getGui().getMessageText().setText("In a quiet rural town, far-far away there was a little house.\nBeside the little house laid an enormous garden, abundant with vegetables and fruits.");
//        Thread.sleep(5000);
//        gui.getMessageText().setText("The oasis of a garden was fenced around for its own good.\nTo feast on the forbidden food, little rabbit a path you must choose.");
//        Thread.sleep(5000);
//        gui.getMessageText().setText("Find the key within the little house to open the gated garden and consume the food.");


        // TODO Henry's eye-burning code ends here, carry on.
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

    public EventHandler geteHandler() {
        return eHandler;
    }

    public void seteHandler(EventHandler eHandler) {
        this.eHandler = eHandler;
    }

}