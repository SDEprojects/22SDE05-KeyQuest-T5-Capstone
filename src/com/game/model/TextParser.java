package com.game.model;

import java.util.Scanner;

public class TextParser {

    public static String[] read() {
        String[] phrase;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter command: ");
        System.out.println(">>> ");
        String parsedText = scanner.nextLine();
        //comment out line above
       //give method value it needs to do the job and test
        String updatedParsedText = parsedText.replaceAll("[^\\w\\s]", "");
        phrase = updatedParsedText.split("\\s+");
        return phrase;
    }
}

