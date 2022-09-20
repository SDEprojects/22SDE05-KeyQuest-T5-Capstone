package com.game.controller;

import com.game.model.Character;
import com.gui.GUIClient;
import com.game.controller.ActionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import static com.game.utility.JSONParser.getAllItems;


public class EventHandler {
    private GUIClient guiClient;
    // To initialize game elements.
    List<String> inventory = new ArrayList<>(); // Initialize the inventory slots.
    Character cat = new Character("cat");
    Character dog = new Character("dog");
    Set<String> listOfItems = getAllItems(); // Initialize the items.

    public EventHandler(GUIClient guiClient) {
        this.guiClient = guiClient;
    }

    public void eventRequest_btn(String lableType, String actionValue) {
        String objClicked = e.getActionCommand();

        // For Start page;
        switch (objClicked) {
            case "2":
                // TODO Generate game page Foyer. Then go to page Foyer.

                break;
            case "3":
                // TODO Load game, future feature.
                break;
            case "4":
                // TODO Settings, future feature.
                break;
            case "5":
                guiClient.getGui().getMessageText().setText("New Game clicked");
                guiClient.getGui().getMessageText().setText("Click 'help' to get available commands, right click items to see what you can do with them.");
                break;
        }


    }


}
