package com.hospitalname.service.util;

public class InputHandler {
    // Capitalize first letter of a string
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) return null;

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String capitalizeFirstLetterOfEachWord(String input) {
        StringBuilder inputBuilder = new StringBuilder();
        String[] inputArr = input.split("\\s+");

        for (String word : inputArr) {
            inputBuilder.append(InputHandler.capitalizeFirstLetter(word)).append(" ");
        }

        return inputBuilder.toString().trim();
    }
}
