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
import static com.game.utility.JSONParser.getStartingRoom;


public class EventHandler {
    private GUIClient guiClient;
    // To initialize game elements.
    private List<String> inventory = new ArrayList<>(); // Initialize the inventory slots.

    private Set<String> listOfItems = getAllItems(); // Initialize the items.

    public EventHandler(GUIClient guiClient) {
        this.guiClient = guiClient;
    }

    // Response to clicking on the buttons.
    public void eventRequest_btn(String lableID, String actionValue) {
        //TODO actionValue from ActionHandler when the label type is Jbutton;
        // When actionValue == click or do.

        if (lableID == "foyer") {
            // TODO Generate game page *Foyer*. Then go to Foyer.
//            guiClient.getGui().generateScreen(2);
            guiClient.getGui().setupRoom();

        } else if (lableID == "kitchen") {
            // TODO Generate game page *Kitchen*. Then go to Kitchen.
        } else if (lableID == "loft") {
            // TODO Generate game page *Loft*. Then go to Loft.
        } else if (lableID == "garage") {
            // TODO Generate game page *Garage*. Then go to Garage.
        } else if (lableID == "lounge") {
            // TODO Generate game page *Lounge*. Then go to Lounge.
        } else if (lableID == "bathroom") {
            // TODO Generate game page *Bathroom*. Then go to Bathroom.
        } else if (lableID == "closet") {
            // TODO Generate game page *Closet*. Then go to Closet.
        } else if (lableID == "garden") {
            // TODO Do check if the player has the key in the inventory, if so, generate winning page.
        } else if (lableID == "help") {
            guiClient.getGui().getMessageText().setText("Click on the items to see what you can do with them.");
        } else if (lableID == "btn_2") {
            // TODO Load game, future feature.
        } else if (lableID == "btn_3") {
            // TODO Settings, future feature.
        } else if (lableID == "btn_4"){
            guiClient.getGui().getMessageText().setText("New Game clicked");
            guiClient.getGui().getMessageText().setText("Click 'help' to get available commands, right click items to see what you can do with them.");
        } else if (lableID == "obj_1" || lableID == "obj_2" || lableID == "obj_3" || lableID == "obj_4") {
            // TODO If the item at the first location, add to inventory, else if lounge or loft, distract dog or cat. else text "no need to use the item here".

        } else if (lableID == "obj_5") {
            // TODO the key. add to inventory.
        } else if (lableID == "ch_1" || lableID == "ch_2") {
            // TODO Talk to dog or cat.
        }
    }




}
