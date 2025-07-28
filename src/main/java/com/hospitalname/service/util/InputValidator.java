package com.hospitalname.service.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class InputValidator {
    public static boolean validateYorN(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(prompt + " (y/n) ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y")) return true;
            if (input.equals("n")) return false;

            System.out.println("Invalid input. Please enter 'y' or 'n' only.");
        }
    }

    /* Validate user input strings, specifically ensuring that the input
    contains only letters (i.e. no symbols or numbers). */
    public static boolean validateStringInput(String input) {
        if (input.matches("[a-zA-Z ]+")) {
            return true;
        } else {
            System.out.println("Invalid input. Only letters are allowed.");
            return false;
        }
    }

    public static boolean validateDateInput(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = null;
        try {
            date = LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date.");
            return false;
        }
    }

    public static boolean validateSexInput(String input) {
        List<String> sexes = List.of("male", "female", "other");
        return sexes.contains(input);
    }
}
