package com.game.controller;

import com.game.model.Introduction;
import com.game.view.MessageArt;
import com.game.view.Screen;
import com.game.utility.TextParser;
import com.game.model.Character;
import com.game.model.Item;
import com.game.model.Location;
import com.gui.GUI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static com.game.utility.JSONParser.*;

public class GameClient implements java.io.Serializable {

    public static void main(String[] args) throws InterruptedException {
        JSONObject jsonObjectCommand = getJsonObjectCommand(); // Load command.json.
        Screen.ClearScreen();
        MessageArt.title(); // Print game title.
        Screen.DivideScreen();
        String currentLocation = getStartingRoom(); // Load starting location from location v3.json.
        Set<String> allItems = getAllItems(); // Initialize items for the game from items.json
        String[] phrase;
        Introduction introduction = new Introduction(); // Initialize game info.
        System.out.println(introduction.getStory());
        System.out.println(introduction.getPlayer());
        System.out.println(introduction.getObjective());
        System.out.println(introduction.getWin()); // TODO I believe this is an extra statement should be deleted.
        Screen.DivideScreen();
        GUI gui = new GUI();

        while (true) {
            String firstCommand = GameManager.start();
            Screen.ClearScreen();
            if (Objects.equals(firstCommand, "quit")) { // Enter quit to quit.
                GameManager.quit();
                break;
            }
            if (Objects.equals(firstCommand, "load")){ // Enter load to load game.
                GameManager.loadGame();
            }

            if (Objects.equals(firstCommand, "start")) { // Type in start to start the game.
                Screen.ClearScreen();
                System.out.println("Type 'help' to get available commands, type 'look' to get list of things you are looking at.");
                System.out.println("List of available commands: " + getKeyCommands());
                System.out.println("List of available locations: " + getListOfLocations());
                List<String> inventory = new ArrayList<>(); // Initialize the inventory slots.
                Character cat = new Character("cat");
                Character dog = new Character("dog");
                Set<String> listOfItems = getAllItems(); // Initialize the items.
                Screen.DivideScreen();
                do {
                    System.out.println("\nCurrent location is " + currentLocation);
                    Screen.DivideScreen();
                    Location location = new Location(currentLocation);
                    String[] listNextLocations = location.getDirections();
                    String[] characters = location.getCharacter();
                    System.out.println(location.getDescription());
                    //System.out.println("\nList of furniture: " + Arrays.toString(location.getFurniture()));
                    // Load NPC (dog) when character is in the current location.
                    if (characters.length != 0) {
                        if (Arrays.asList(characters).contains("cat")) {
                            System.out.println(cat.getDescription());
                        } else if (Arrays.asList(characters).contains("dog")) {
                            System.out.println(dog.getDescription());
                        }
                    }
                    // Print items at current location.
                    System.out.println("Items that can be found in this room: " + Arrays.toString(location.getItems()));
                    Screen.DivideScreen();
                    // Print available exits of current location.
                    System.out.println("You can go to: " + Arrays.toString(listNextLocations));
                    Screen.DivideScreen();
                    // User input reader, get the verb and noun.
                    phrase = TextParser.read();
                    String item;
                    Set<String> character = getCharacters();
                    boolean isValidVerb = false;
                    boolean isValidLocation = false;
                    boolean isValidItem = false;
                    boolean isValidCharacter = false;
                    Screen.ClearScreen();
                    for (int i = 0; i < phrase.length; i++) {
                        // verify the command is valid and the item or location is valid.
                        if (phrase.length == 2) {
                            isValidVerb = jsonObjectCommand.has(phrase[0]);
                            isValidLocation = getRooms().has(phrase[1]);
                            for (int j = 0; j < getLocationItems(currentLocation).length; j++) {
                                item = getLocationItems(currentLocation)[j];
                                if (Objects.equals(item, phrase[1])) {
                                    isValidItem = true;
                                }
                            }
                            if (character.contains(phrase[1])) {
                                isValidCharacter = true;
                            }
                        } else if (phrase.length == 1) {
                            isValidVerb = jsonObjectCommand.has(phrase[0]);
                        }
                    }
                    if (Objects.equals(phrase[0], "save")) {
                        GameManager.saveGame();
                        break;
                    }
                    else if (Objects.equals(phrase[0], "load")) {
                        GameManager.loadGame();
                    }
                    // TODO: Update validation with objects on screen
                    else if (isValidVerb && isValidLocation) {
                        JSONArray nextCommandsJsonArray = jsonObjectCommand.getJSONArray(phrase[0]);
                        String[] nextLocations = location.getDirections();
                        String[] nextCommands = getStringArray(nextCommandsJsonArray);
                        for (String nextLocation : nextLocations) {
                            Location nextRoomLocation = new Location(phrase[1]);
                            String[] charactersInNextLocation = nextRoomLocation.getCharacter();

                            // TODO: This will be an action coming from Java Swing, object clicked on screen will trigger these checks
                            if (Arrays.asList(nextLocations).contains(phrase[1]) && (Arrays.asList(nextCommands).contains(phrase[1]))) {
                                // When the player enter the garden from garage with the key. Give the player win screen.
                                if (inventory.contains("key") && Objects.equals(currentLocation, "garage") && Objects.equals(phrase[1], "garden")) {
                                    System.out.println(introduction.getWin());
                                    currentLocation = phrase[1];
                                    Screen.DivideScreen();
                                    MessageArt.win();
                                    GameManager.quit();
                                    phrase[0] = "quit";
                                    break;
                                    // If the player enter the garden from garage with NO key, tell them they need the key.
                                } else if (!inventory.contains("key") && Objects.equals(currentLocation, "garage") && Objects.equals(phrase[1], "garden")) {
                                    System.out.println(introduction.getPrompt());
                                    break;
                                    // When there is an NPC at the area, tell the player they need to distract them.
                                } else if (!inventory.isEmpty() && charactersInNextLocation.length != 0) {
                                    System.out.println("Distract " + charactersInNextLocation[0]);
                                    Screen.DivideScreen();
                                    Character characterToDistract = new Character(charactersInNextLocation[0]);
                                    System.out.println(characterToDistract.getDescription());
                                    while (true) {
                                        Screen.DivideScreen();
                                        System.out.println("Throw the item to distract " + charactersInNextLocation[0]);
                                        System.out.println("List of inventory items: " + inventory);
                                        Screen.DivideScreen();
                                        phrase = TextParser.read();
                                        if ((Objects.equals(phrase[0], "throw") || Objects.equals(phrase[0], "drop")) && inventory.contains(phrase[1])) {
                                            inventory.remove(phrase[1]);
                                            Screen.ClearScreen();
                                            System.out.println("You distracted: " + charactersInNextLocation[0] + " and came to the next room: " + nextRoomLocation.getName());
                                            break;
                                        }
                                    }
                                    // When the player doesn't have anything to distract the NPC, tell them to go get the items.
                                } else if (inventory.isEmpty() && charactersInNextLocation.length != 0) {
                                    System.out.println(introduction.getLose());
                                    System.out.println("Get items to distract cat and dog, before going to " + phrase[1]);
                                    Screen.DivideScreen();
                                    MessageArt.over();
                                    GameManager.quit();
                                    phrase[0] = "quit";
                                    break;
                                }
                                currentLocation = String.valueOf(nextRoomLocation.getName());
                                break;
                                // Location input check, can't go to current location, can't go to the location not available.
                            } else if (phrase[1].equals(currentLocation)) {
                                System.out.println("Already in " + phrase[1]);
                                break;
                            } else if (!phrase[1].equals(nextLocation)) {
                                System.out.println("Cannot " + phrase[0] + ": " + currentLocation + " ---> " + phrase[1]);
                                break;
                            } else if (!Arrays.asList(nextCommands).contains(phrase[1])) {
                                System.out.println("Cannot " + phrase[0] + " " + phrase[1]);
                                break;
                            }
                        }
                    } else if (isValidItem) {
                        // Return statement when player try to get something they already have.
                        if ((inventory.contains(phrase[1]) && Objects.equals(phrase[0], "get")) || (inventory.contains(phrase[1]) && Objects.equals(phrase[0], "pick")) || (inventory.contains(phrase[1]) && Objects.equals(phrase[0], "collect")) || (inventory.contains(phrase[1]) && Objects.equals(phrase[0], "grab")) || (inventory.contains(phrase[1]) && Objects.equals(phrase[0], "take"))) {
                            System.out.println("Inventory already has " + phrase[1]);
                            System.out.println(inventory);
                        // Consume an item by drop or eat.
                        } else if (inventory.contains(phrase[1]) && (Objects.equals(phrase[0], "drop") || Objects.equals(phrase[0], "eat") || Objects.equals(phrase[0], "throw"))) {
                            inventory.remove(phrase[1]);
                            System.out.println(phrase[0] + " " + phrase[1] + " done");
                            System.out.println("Removed " + phrase[1] + " from the inventory");
                            System.out.println(inventory);
                        // Get the item.
                        } else if ((!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "get")) || (!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "pick")) || (!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "collect")) || (!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "grab")) || (!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "take"))) {
                            inventory.add(phrase[1]);
                            System.out.println("Added " + phrase[1] + " to the inventory");
                            System.out.println(inventory);
                        // Interaction check with the item.
                        } else if (inventory.contains(phrase[1]) && (!Objects.equals(phrase[0], "get") || !Objects.equals(phrase[0], "pick") || !Objects.equals(phrase[0], "collect") || !Objects.equals(phrase[0], "grab") || !Objects.equals(phrase[0], "take"))) {
                            System.out.println("Cannot " + phrase[0] + " " + phrase[1]);
                        // Inventory check when try to use the item.
                        } else if ((!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "drop")) || (!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "eat")) || (!inventory.contains(phrase[1]) && Objects.equals(phrase[0], "throw"))) {
                            System.out.println("Inventory doesn't  contain " + phrase[1]);
                            System.out.println(inventory);
                        // Cover all else situation that can't be executed.
                        } else {
                            System.out.println("Cannot " + phrase[0] + " " + phrase[1]);
                        }
                    // Show inventory.
                    } else if ((Objects.equals(phrase[0], "inventory") || (Objects.equals(phrase[0], "show") && Objects.equals(phrase[1], "inventory")))) {
                        System.out.println("List of inventory items " + inventory);
                    // Text return when talk to NPC.
                    } else if (Objects.equals(phrase[0], "talk")) {
                        //System.out.println("\nWho would you like to talk to: " + getCharacters());
                        if (isValidCharacter && Objects.equals(phrase[1], "dog")) {
                            System.out.println("You are talking to " + phrase[1]);
                            System.out.println(getDogSpeech());
                        } else if (isValidCharacter && Objects.equals(phrase[1], "cat")) {
                            System.out.println("You are talking to " + phrase[1]);
                            System.out.println(getCatSpeech());
                        }
                    // Help text.
                    } else if (Objects.equals(phrase[0], "help")) {
                        System.out.println("\nList of available commands: " + getKeyCommands());
                        System.out.println("List of all items " + allItems);
                    } else if (Objects.equals(phrase[0], "look") && phrase.length == 2) {
                        if (listOfItems.contains(phrase[1])) {
                            Item itemInformation = new Item(phrase[1]);
                            System.out.println("You can find " + phrase[1] + " in " + itemInformation.getRoom());
                            System.out.println(getLookItem(phrase[1]));
                            System.out.println(itemInformation.getUsage());
                        }
                    // Look function, didn't use in the current version of game.
                    } else if (Objects.equals(phrase[0], "look") && phrase.length == 1) {
                        System.out.println("You are looking at " + Arrays.toString(location.getFurniture()));
                        System.out.println("There are following items in this room " + Arrays.toString(location.getItems()));
                    // Quit function with confirmation check.
                    } else if (Objects.equals(phrase[0], "quit")) {
                        String confirmation = GameManager.confirmQuit();
                        if (Objects.equals(confirmation, "yes")) {
                            GameManager.quit();
                            Screen.DivideScreen();
                            break;
                        } else if (Objects.equals(confirmation, "no")) {
                            phrase[0] = "start";
                        }
                    } else if (phrase.length == 2 && ((inventory.contains(phrase[1]) && (Objects.equals(phrase[0], "drop") || Objects.equals(phrase[0], "eat") || Objects.equals(phrase[0], "throw"))))) {
                        inventory.remove(phrase[1]);
                        System.out.println(phrase[0] + " " + phrase[1] + " done");
                        System.out.println("Removed " + phrase[1] + " to the inventory");
                        System.out.println(inventory);
                    } else {
                        System.out.println("Please try another command. Please type 'help' for more information.");
                    }
                }
                while (!Objects.equals(phrase[0], "quit"));
                break;
            }
        }
    }
}