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
    String currentLocation = getStartingRoom();
    Location location = new Location(currentLocation);
    String[] listNextLocations = location.getDirections();

    String[] characters = location.getCharacter();
    int musicCounter = 2;
    // Set up game actions ends.

    public EventHandler(GUIClient guiClient) {
        this.guiClient = guiClient;
    }

    // Create function to track the location.
    public void roomSetup(String actionValue) {
        // Track location and items.
        currentLocation = actionValue;
        listNextLocations = getLocationDirections(actionValue);
        // Set text box.
        String[] itemsHere = getLocationItems(actionValue);
        String roomName = currentLocation.substring(0, 1).toUpperCase() + currentLocation.substring(1); // Capitalize the first letter of room name.
        guiClient.getGui().getMessageText().setText(getLocationDescription(actionValue));
        if (itemsHere.length == 0) {
            guiClient.getGui().getMessageText().setText(roomName + ": " + getLocationDescription(actionValue) + ".\nNo items found here.\nYou can go to: " + Arrays.toString(listNextLocations));
        } else if (itemsHere.length > 0) {
            guiClient.getGui().getMessageText().setText(roomName + ": " + getLocationDescription(actionValue) + ".\nItems that can be found in this room: " + Arrays.toString(itemsHere) + ".\nYou can go to: " + Arrays.toString(listNextLocations));
        }
        // TODO Will add Cat and dog later.
    }


    // Response to clicking on the buttons.
    public void eventRequest(String lableID, String actionValue)  {
        //TODO actionValue from ActionHandler when the label type is Jbutton;
        // When actionValue == click or do.
        Location nextRoomLocation = new Location(actionValue);
        if (actionValue.equals("foyer")) {
            System.out.println("Create a new page of Foyer");
            guiClient.getGui().generateScreen(1); // Set up the page with direction arrows.
            roomSetup(actionValue);

        } else if (actionValue.equals("kitchen")) {
            System.out.println("Create a new page of Kitchen");
            guiClient.getGui().generateScreen(2);
            roomSetup(actionValue);
            if (actionValue.equals("drumstick")) {
                inventory.add("drumstick");
                guiClient.getGui().getMessageText().setText("Drumstick is added to inventory");
            } else if (actionValue.equals("cucumber")) {
                inventory.add("cucumber");
                guiClient.getGui().getMessageText().setText("Cucumber is added to inventory");
            }
            // TODO move items to inventory slots.
        } else if (actionValue.equals("loft")) {
            guiClient.getGui().generateScreen(3);
            // TODO Don't generate until user throw the item to the dog.
            guiClient.getGui().getMessageText().setText("An alert dog watching out for lurking intruders.");
            Character dog = new Character("dog");
            // If click on the dog, means talk to the dag, if click the item, means throw.
            if (actionValue.equals("dog")) {
                guiClient.getGui().getMessageText().setText(String.valueOf(dog.getSpeech()));
            } else if (actionValue.equals("cucumber") || actionValue.equals("wool") || actionValue.equals("drumstick") || actionValue.equals("shoes")) {
                //TODO populate direction arrows.
                guiClient.getGui().getMessageText().setText("You distracted dog, you can go to Garage, Kitchen or Lounge.");
            }

        } else if (actionValue.equals("garage")) {
            guiClient.getGui().generateScreen(4);
            roomSetup(actionValue);
            if (actionValue.equals("key")) {
                inventory.add("key");
                guiClient.getGui().getMessageText().setText("You got a key!!");
            }

        } else if (actionValue.equals("lounge")) {
            if (inventory.size() != 0) {
                guiClient.getGui().generateScreen(5);
                guiClient.getGui().getMessageText().setText("A curious and sneaky cat looking for the next toy to play and scratch with.");
                Character cat = new Character("cat");
                // If click on the dog, means talk to the dag, if click the item, means throw.
                if (actionValue.equals("cat")) {
                    guiClient.getGui().getMessageText().setText(String.valueOf(cat.getSpeech()));
                } else if (actionValue.equals("cucumber") || actionValue.equals("wool") || actionValue.equals("drumstick") || actionValue.equals("shoes")) {
                    //TODO populate direction arrows.
                    guiClient.getGui().getMessageText().setText("You distracted cat, you can go to Bathroom, Kitchen, Loft or Foyer.");
                }
            }
            guiClient.getGui().generateScreen(9);
            guiClient.getGui().getMessageText().setText(getIntroductionLose() + "\nGet items to distract cat and dog, before going to Lounge");

        } else if (actionValue.equals("bathroom")) {
            guiClient.getGui().generateScreen(6);
            roomSetup(actionValue);
        } else if (actionValue.equals("closet")) {
            guiClient.getGui().generateScreen(7);
            roomSetup(actionValue);
            if (actionValue.equals("shoes")) {
                inventory.add("shoes");
                guiClient.getGui().getMessageText().setText("Shoes are added to inventory");
            } else if (actionValue.equals("wool")) {
                inventory.add("wool");
                guiClient.getGui().getMessageText().setText("Wool is added to inventory");
            }
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
