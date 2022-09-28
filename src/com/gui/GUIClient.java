package com.gui;

import com.game.controller.ActionHandler;
import com.game.controller.EventHandler;
import com.sound.Sound;

public class GUIClient {

    private Sound sound = new Sound();

    private Sound music = new Sound();

    private ActionHandler aHandler = new ActionHandler(this);

    private EventHandler eHandler = new EventHandler(this);

    private GUI gui = new GUI(this);

    public static void main(String[] args) {
        new GUIClient();
    }

    public GUIClient() {
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

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Sound getMusic() {
        return music;
    }

    public void setMusic(Sound music) {
        this.music = music;
    }
}