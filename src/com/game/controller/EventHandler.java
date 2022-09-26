package com.game.controller;
import com.sound.*;
import com.gui.*;
import com.game.model.Character;
import com.game.model.Location;
import com.game.utility.JSONParser;
import com.game.utility.Room;
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
    private List<String> stdRm = new ArrayList<>(Arrays.asList("foyer", "kitchen", "loft", "garage", "lounge", "bathroom", "closet")); // Use this to get the bgNum from the index number.
    private Set<String> characters = getCharacters();
    int musicCounter = 2;
    boolean dogDistracted = false;
    boolean catDistracted = false;
    // Set up game actions ends.

    public EventHandler(){
        super();
    }
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
        System.out.println(stdRm.indexOf(actionValue) + 1);
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
            // TODO Here should populate the navigation buttons.
        } else if (currentLocation.equals("lounge")) {
            // If the item is clicked to distract cat.
            guiClient.getGui().getMessageText().setText("You distracted the cat.");
            catDistracted = true;
            // TODO Here should populate the navigation buttons.
        } else {
            guiClient.getGui().getMessageText().setText("You can't use this item here.");
        }
    }
    // Talk NPC function.
    public void talkToCh(String actionValue) {
    // Random speech.
        if (actionValue.equals("dog")){
            guiClient.getGui().getMessageText().setText(getDogSpeech());
        } else if (actionValue.equals("cat")){
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
        } else if(actionValue.equals("quit")) { // Quit the game.
            System.exit(0);
        } else if(actionValue.equals("play again")) { // Quit the game.
            new GUIClient();
        } else if(stdRm.contains(actionValue)) { // Go through regular rooms.
            roomSetup(actionValue);
        } else if(characters.contains(actionValue)) { // When click on dog or cat, means talk to them.
            talkToCh(actionValue);
        } else if (getAllItems().contains(actionValue)){ // When click on items, use or add to inventory.
            itemAction(actionValue);
        } else if (actionValue.equals("garden")) { // When try to go garden, if(key) win, no key, tell them find the key.
            if (inventory.contains("key")) {
                guiClient.getGui().generateScreen(8);
                guiClient.getGui().getMessageText().setText(getIntroductionWin());
            }
            guiClient.getGui().getMessageText().setText(getIntroductionPrompt());
        }
    }

    public String eventRequest_help(String actionValue){

        return null;
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
