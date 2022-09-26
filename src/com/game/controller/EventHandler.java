package com.game.controller;

import com.gui.*;
import com.game.model.Location;

import java.util.*;

import static com.game.utility.JSONParser.*;


public class EventHandler {
    private GUIClient guiClient;
    // To initialize game elements.
    private List<String> inventory = new ArrayList<>(); // Initialize the inventory slots.
    private GUI gui;
    private Set<String> listOfItems = getAllItems(); // Initialize the items.
    // Set up the game actions.
    private String currentLocation = getStartingRoom();
    private Location location = new Location(currentLocation);
    private String[] listNextLocations = location.getDirections();
    private List<String> stdRm = new ArrayList<>(Arrays.asList("foyer", "kitchen", "placeholder", "garage", "placeholder", "bathroom", "closet")); // Use this to get the bgNum from the index number.
    private List<String> npcRm = new ArrayList<>(Arrays.asList("placeholder", "placeholder", "loft", "placeholder", "lounge")); // Use this to get the bgNum from the index number.
    private Set<String> characters = getCharacters();
    int musicCounter = 2;
    boolean dogDistracted = false;
    boolean catDistracted = false;
    boolean box1 = false;
    boolean box2 = false;
    // Set up game actions ends.

    public EventHandler(){
        super();
    }
    public EventHandler(GUIClient guiClient) {
        this.guiClient = guiClient;
    }

    // Create function to setup rooms.
    public void roomSetup(String actionValue) {
        // Track location and items.

        guiClient.getGui().generateScreen(stdRm.indexOf(actionValue) + 1);
        catDistracted = false;
        dogDistracted = false;
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
    }

    // Create function to setup the rooms with dog and cat.
    public void npcRmSetup(String actionValue) {
        if (inventory.size() > 0){ // Check player inventory to see if they are empty or not.
            guiClient.getGui().generateScreen(npcRm.indexOf(actionValue) + 1);
            guiClient.getGui().getMessageText().setText(getLocationDescription(actionValue));
            currentLocation = actionValue;
            listNextLocations = getLocationDirections(actionValue);

            if (dogDistracted && actionValue.equals("loft")){
                guiClient.getGui().generateNpcScreen(3, "dog");
                guiClient.getGui().getMessageText().setText("You distracted the dog.\nYou can go to: " + Arrays.toString(listNextLocations));
//                dogDistracted = false;
            } else if (catDistracted && actionValue.equals("lounge")) {
                guiClient.getGui().generateNpcScreen(5, "cat");
                guiClient.getGui().getMessageText().setText("You distracted the cat.\nYou can go to: " + Arrays.toString(listNextLocations));
//                catDistracted = false;
            }
        } else if (inventory.size() == 0) {
            guiClient.getGui().generateScreen(9);
            guiClient.getGui().getMessageText().setText(getIntroductionLose() + "\nGet items to distract cat or dog, before going to their territory.");
        }

    }

    // roomSetup returns the integer background number for GUI.
    public int roomSetup_int(String actionValue) {
        // Track location and items.
        /*guiClient.getGui().generateScreen(stdRm.indexOf(actionValue) + 1);
        currentLocation = actionValue;
        listNextLocations = getLocationDirections(actionValue);
        // Set text box.
        String[] itemsHere = getLocationItems(actionValue);
        String roomName = currentLocation.substring(0, 1).toUpperCase() + currentLocation.substring(1); // Capitalize the first letter of room name.
        if (itemsHere.length == 0) {
            guiClient.getGui().getMessageText().setText(roomName + ": " + getLocationDescription(actionValue) + ".\nNo items found here.\nYou can go to: " + Arrays.toString(listNextLocations));
        } else if (itemsHere.length > 0) {
            guiClient.getGui().getMessageText().setText(roomName + ": " + getLocationDescription(actionValue) + ".\nItems that can be found in this room: " + Arrays.toString(itemsHere) + ".\nYou can go to: " + Arrays.toString(listNextLocations));
        }*/
        // TODO Will add Cat and dog later.
        return (stdRm.indexOf(actionValue) + 1);
    }

    // roomSetup returns the string for GUI text box
    public String roomSetup_string(String actionValue) {
        // Track location and items.
        currentLocation = actionValue;
        listNextLocations = getLocationDirections(actionValue);
        // Set text box.
        String[] itemsHere = getLocationItems(actionValue);
        String roomName = currentLocation.substring(0, 1).toUpperCase() + currentLocation.substring(1); // Capitalize the first letter of room name.
        if (itemsHere.length == 0) {
            return (roomName + ". " + getLocationDescription(actionValue) + ". " + Arrays.toString(listNextLocations) + ".");
        } else if (itemsHere.length > 0) {
            return (roomName + ": " + getLocationDescription(actionValue) + ".\nItems that can be found in this room: " + Arrays.toString(itemsHere) + ".\nYou can go to: " + Arrays.toString(listNextLocations));
        }
        return "this was empty";
        // TODO Will add Cat and dog later.

    }

    // Use or pick the item.
    public void itemInventoryAction(String actionValue) {
//        If the item is at the found location. Add to inventory, or if already has it, do something else.
        if (getItemRoom(actionValue).equals(currentLocation)) {
            // Already have it
            if (inventory.contains(actionValue)) {
                guiClient.getGui().getMessageText().setText("You already have " + actionValue + " in your inventory.");
            } else {
                // Don't have it yet
                if (!box1){
                    inventory.add(actionValue);
                    guiClient.getGui().setBox1(actionValue);
                    guiClient.getGui().removeObj(actionValue);
                    guiClient.getGui().getMessageText().setText("Added " + actionValue + " to your inventory.");
                    box1 = true;
                } else if (box1 && !box2) {
                    inventory.add(actionValue);
                    guiClient.getGui().setBox2(actionValue);
                    guiClient.getGui().removeObj(actionValue);
                    guiClient.getGui().getMessageText().setText("Added " + actionValue + " to your inventory.");
                    box2 = true;
                } else if (box1 && box2) {
                    guiClient.getGui().getMessageText().setText("You are a bunny, you can only have two items at a time.\nI don't even know how you carry two items together, but that's what Team 7 said.");
                }

            }
        }
    }

    // Talk NPC function.
    public void talkToCh(String actionValue) {
        // Random speech.
        if (actionValue.equals("dog")) {
            guiClient.getGui().getMessageText().setText(getDogSpeech());
        } else if (actionValue.equals("cat")) {
            guiClient.getGui().getMessageText().setText(getCatSpeech());
        }
    }

    // Response to clicking on the buttons.
    public void eventRequest(String actionValue) {

        if (actionValue.equals("story")) { // Main page, click "story".
            guiClient.getGui().getMessageText().setText(getIntroductionStory()); // TODO need to fix format, stroll or decrease the text.
            guiClient.getGui().getMessageText().setVisible(true);
        } else if (actionValue.equals("music player")) { // To start or stop background music.
            musicCounter++;
            if (musicCounter % 2 == 0) {
                guiClient.getGui().stopMusic();
                guiClient.getGui().getMessageText().setText("Music Off.");
                guiClient.getGui().getMessageText().setVisible(true);
            } else {
                guiClient.getGui().playMusic(0);
                guiClient.getGui().getMessageText().setText("Music On.");
                guiClient.getGui().getMessageText().setVisible(true);
            }
        } else if (actionValue.equals("help")) { // Help in the main page.
            guiClient.getGui().getMessageText().setText(getIntroductionPlayer() + "\nUse the items you found to distract mean animals, \nfind the key to unlock the garden, \nthen enjoy the carrot.");
            guiClient.getGui().getMessageText().setVisible(true);
        } else if (actionValue.equals("quit")) { // Quit the game.
            System.exit(0);
        } else if (actionValue.equals("play again")) { // Quit the game.
            new GUIClient();
        } else if (stdRm.contains(actionValue)) { // Go through regular rooms.
            roomSetup(actionValue);
        } else if (npcRm.contains(actionValue)) { // Go through regular rooms.
            npcRmSetup(actionValue);
        } else if (characters.contains(actionValue)) { // When click on dog or cat, means talk to them.
            talkToCh(actionValue);
        } else if (actionValue.equals("box1")) {
            if (currentLocation.equals("loft") && !dogDistracted) {
                // If the item is clicked to distract dog.
                dogDistracted = true;
                npcRmSetup("loft");
                inventory.remove(0);
                guiClient.getGui().setBox1("empty");
                box1 = false;

            } else if (currentLocation.equals("lounge") && !catDistracted) {
                // If the item is clicked to distract cat.
                catDistracted = true;
                npcRmSetup("lounge");
                inventory.remove(0);
                guiClient.getGui().setBox1("empty");
                box1 = false;
            } else {
                guiClient.getGui().getMessageText().setText("You can't use this item here.");
            }
        } else if (actionValue.equals("box2")) {
            if (currentLocation.equals("loft") && !dogDistracted) {
                // If the item is clicked to distract dog.
                dogDistracted = true;
                npcRmSetup("loft");
                inventory.remove(0);
                guiClient.getGui().setBox2("empty");
                box2 = false;

            } else if (currentLocation.equals("lounge") && !catDistracted) {
                // If the item is clicked to distract cat.
                catDistracted = true;
                npcRmSetup("lounge");
                inventory.remove(0);
                guiClient.getGui().setBox2("empty");
                box2 = false;
            } else {
                guiClient.getGui().getMessageText().setText("You can't use this item here.");
            }
        } else if (getAllItems().contains(actionValue)) { // When click on items, use or add to inventory.
            itemInventoryAction(actionValue);
        } else if (actionValue.equals("garden")) { // When try to go garden, if(key) win, no key, tell them find the key.
            if (inventory.contains("key")) {
                guiClient.getGui().generateScreen(8);
                guiClient.getGui().getMessageText().setText(getIntroductionWin()); //
            } else {
                guiClient.getGui().getMessageText().setText(getIntroductionPrompt());
            }
        }
    }

    public String eventRequest_help(String actionValue){

        return getIntroductionPlayer() + "\nUse the items you found to distract mean animals, \nfind the key to unlock the garden, \nthen enjoy the carrot.";
    }

    /*public void winGame(String actionValue) {
        //while the player is in the garage, if they have the key, game goes to win screen
        //while (location.equals("garage")) {
            if (inventory.equals("key") && location.equals("garage")) {
                guiClient.getGui().generateScreen(9);
                guiClient.getGui().playSE(9);
                guiClient.getGui().getMessageText().setText("Congratulations! You win the game!");

            }else {
                    ///stay in garage? with current inventory?
    }*/
}
