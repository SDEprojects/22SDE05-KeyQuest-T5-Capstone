package com.game.controller;

import com.game.model.Character;
import com.game.model.Location;
import com.game.utility.JSONParser;
import com.game.utility.Room;
import com.gui.GUIClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.game.utility.JSONParser.*;


public class EventHandler {
    private GUIClient guiClient;
    // To initialize game elements.
    private List<String> inventory = new ArrayList<>(); // Initialize the inventory slots.

    private Set<String> listOfItems = getAllItems(); // Initialize the items.
    // Set up the game actions.
    private String currentLocation = getStartingRoom();
    private Location location = new Location(currentLocation);
    private String[] listNextLocations = location.getDirections();
    private List<String> stdRm = new ArrayList<>(Arrays.asList("foyer", "kitchen", "loft", "garage", "lounge", "bathroom", "closet")); // Use this to get the bgNum from the index number.
    private Set<String> characters = getCharacters();
    int musicCounter = 2;
    boolean dogDistracted = false;
    boolean catDistracted = false;
    // Set up game actions ends.

    public EventHandler(GUIClient guiClient) {
        this.guiClient = guiClient;
    }

    // Create function to track the location.
    public void roomSetup(String actionValue) {
        // Track location and items.
        guiClient.getGui().generateScreen(stdRm.indexOf(actionValue) + 1);
        currentLocation = actionValue;
        listNextLocations = getLocationDirections(actionValue);
        // Set text box.
        String[] itemsHere = getLocationItems(actionValue);
        String roomName = currentLocation.substring(0, 1).toUpperCase() + currentLocation.substring(1); // Capitalize the first letter of room name.
//        guiClient.getGui().getMessageText().setText(getLocationDescription(actionValue));
        if (itemsHere.length == 0) {
            guiClient.getGui().getMessageText().setText(roomName + ": " + getLocationDescription(actionValue) + ".\nNo items found here.\nYou can go to: " + Arrays.toString(listNextLocations));
        } else if (itemsHere.length > 0) {
            guiClient.getGui().getMessageText().setText(roomName + ": " + getLocationDescription(actionValue) + ".\nItems that can be found in this room: " + Arrays.toString(itemsHere) + ".\nYou can go to: " + Arrays.toString(listNextLocations));
        }
        // TODO Will add Cat and dog later.
    }

    // Use or pick the item.
    public void itemAction(String actionValue) {
//        If the item is at the found location. Add to inventory, or if already has it, do something else.
        if (getItemRoom(actionValue).equals(currentLocation)) {
            // Already have it
            if (inventory.contains(actionValue)) {
                guiClient.getGui().getMessageText().setText("You already have " + actionValue + " in your inventory.");
            } else {
                // Do have it yet
                inventory.add(actionValue);
                guiClient.getGui().getMessageText().setText("Added " + actionValue + " to your inventory.");
            }
        } else if (currentLocation.equals("loft")) {
            // If the item is clicked to distract dog.
            guiClient.getGui().getMessageText().setText("You distracted the dog.");
            dogDistracted = true;
        } else if (currentLocation.equals("lounge")) {
            // If the item is clicked to distract cat.
            guiClient.getGui().getMessageText().setText("You distracted the cat.");
            catDistracted = true;
        } else {
            guiClient.getGui().getMessageText().setText("You can't use this item here.");
        }
    }
    // Talk NPC function.
    public void talkToCh(String actionValue) {
    // Random speech, with one random kill, :).

    }
    // TODO

    // Response to clicking on the buttons.
    public void eventRequest(String actionValue) {
        //TODO actionValue from ActionHandler when the label type is Jbutton;
        // When actionValue == click or do.

        if (stdRm.contains(actionValue)) {
            roomSetup(actionValue);

        } else if(characters.contains(actionValue)){
            talkToCh(actionValue);
        } else if (actionValue.equals("garden")) {
            if (inventory.contains("key")) {
                guiClient.getGui().generateScreen(8);
                guiClient.getGui().getMessageText().setText(getIntroductionWin());
            }
            guiClient.getGui().getMessageText().setText(getIntroductionPrompt());
        } else if (actionValue.equals("help")) {
            guiClient.getGui().getMessageText().setText("Click on the items to see what you can do with them.");
        } else if (actionValue.equals("btn_2")) {
            // TODO Load game, future feature.
        } else if (actionValue.equals("music")) {
            musicCounter++;
            if (musicCounter % 2 == 0) {
                guiClient.getGui().stopMusic();
                System.out.println("stop");
            } else {
                guiClient.getGui().playMusic(0);
                System.out.println("play");
            }

        } else if (actionValue.equals("help")) {
            guiClient.getGui().getMessageText().setText("TODO need to add help text.");
        } else if (actionValue.equals("obj_1") || actionValue.equals("obj_2") || actionValue.equals("obj_3") || actionValue.equals("obj_4")) {
            // TODO If the item at the first location, add to inventory, else if lounge or loft, distract dog or cat. else text "no need to use the item here".


        } else if (actionValue.equals("ch_1") || actionValue.equals("ch_2")) {
            // TODO Talk to dog or cat.
        }
    }
}
