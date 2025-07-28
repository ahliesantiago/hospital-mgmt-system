package com.hospitalname.service.util;

public class InputHandler {
    // Capitalize first letter of a string
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) return null;

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
