package com.game.controller;

import com.game.model.Character;
import com.game.model.Location;
import com.gui.GUIClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


import static com.game.utility.JSONParser.getAllItems;
import static com.game.utility.JSONParser.getStartingRoom;


public class EventHandler {
    private GUIClient guiClient;
    // To initialize game elements.
    private List<String> inventory = new ArrayList<>(); // Initialize the inventory slots.

    private Set<String> listOfItems = getAllItems(); // Initialize the items.
    // Set up the game actions.
    String currentLocation = getStartingRoom();
    Location location = new Location(currentLocation);
    String[] listNextLocations = location.getDirections();
    String[] characters = location.getCharacter();
    // Set up game actions ends.

    public EventHandler(GUIClient guiClient) {
        this.guiClient = guiClient;
    }

    // Response to clicking on the buttons.
    public void eventRequest(String lableID, String actionValue) {
        //TODO actionValue from ActionHandler when the label type is Jbutton;
        // When actionValue == click or do.

        if (actionValue.equals("foyer")) {
            System.out.println("Create a new page of Foyer");
            guiClient.getGui().generateScreen(2); // Set up the page with direction arrows.
            // Inventory slots remain empty. Only action here is going to the kitchen.
            guiClient.getGui().getMessageText().setText("You don't see anything valuable here.\nYou can go to " + Arrays.toString(listNextLocations));
        } else if (actionValue.equals("kitchen")) {
            System.out.println("Create a new page of Kitchen");
            guiClient.getGui().generateScreen(3);
            guiClient.getGui().getMessageText().setText("You are in the Kitchen, there is a drumstick and cucumber.\n You can go to " + Arrays.toString(listNextLocations));
            if (actionValue.equals("drumstick")){
                inventory.add("drumstick");
                guiClient.getGui().getMessageText().setText("Drumstick is added to inventory");
            } else if (actionValue.equals("cucumber")) {
                inventory.add("cucumber");
                guiClient.getGui().getMessageText().setText("Cucumber is added to inventory");
            }
            // TODO move items to inventory slots.
        } else if (actionValue.equals("loft")) {
            guiClient.getGui().generateScreen(4);
            // Don't generate until user throw the item to the dog.

            guiClient.getGui().getMessageText().setText("An alert dog watching out for lurking intruders.");
            Character dog = new Character("dog");
            // If click on the dog, means talk to the dag, if click the item, means throw.
            if (actionValue.equals("dog")) {
                guiClient.getGui().getMessageText().setText(String.valueOf(dog.getSpeech()));
            } else if (actionValue.equals("drumstick")) {
                //TODO populate direction arrows.
                guiClient.getGui().getMessageText().setText("You distracted dog, you can go to Garage, Kitchen or Lounge.");
            }

        } else if (actionValue.equals("garage")) {
            guiClient.getGui().generateScreen(5);
            guiClient.getGui().getMessageText().setText("You are in the Garage. There are a lot of shiny items in here!");

        } else if (actionValue.equals("lounge")) {
            // TODO Generate game page *Lounge*. Then go to Lounge.
        } else if (actionValue.equals("bathroom")) {
            // TODO Generate game page *Bathroom*. Then go to Bathroom.
        } else if (actionValue.equals("closet")) {
            // TODO Generate game page *Closet*. Then go to Closet.
        } else if (actionValue.equals("garden")) {
            // TODO Do check if the player has the key in the inventory, if so, generate winning page.
        } else if (actionValue.equals("help")) {
            guiClient.getGui().getMessageText().setText("Click on the items to see what you can do with them.");
        } else if (actionValue.equals("btn_2")) {
            // TODO Load game, future feature.
        } else if (actionValue.equals("btn_3")) {
            // TODO Settings, future feature.
        } else if (actionValue.equals("btn_4")) {
            guiClient.getGui().getMessageText().setText("Click 'help' to get available commands, right click items to see what you can do with them.");
        } else if (actionValue.equals("obj_1") || actionValue.equals("obj_2") || actionValue.equals("obj_3") || actionValue.equals("obj_4")) {
            // TODO If the item at the first location, add to inventory, else if lounge or loft, distract dog or cat. else text "no need to use the item here".

        } else if (actionValue.equals("obj_5")) {
            // TODO the key. add to inventory.
        } else if (actionValue.equals("ch_1") || actionValue.equals("ch_2")) {
            // TODO Talk to dog or cat.
        }
    }


}
