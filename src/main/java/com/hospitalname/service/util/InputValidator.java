package com.hospitalname.service.util;

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
}
