package com.game.model;

import static com.game.utility.JSONParser.*;

public class Character {
    private final String character;
    private final String name;
    private final String description;
    private final String[] speech;


    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getSpeech() {
        return speech;
    }

    public Character(String character) {
        this.character = character;
        name = getCharacterName(character);
        description = getCharacterDescription(character);
        speech = getCharacterSpeech(character);
    }
}
