package com.hospitalname.cli;
import java.awt.*;
import java.util.Scanner;
import com.hospitalname.cli.MenuHandler;

public class UserWelcome {
    public void start() {
        System.out.println("Welcome!");

//        verifyUser();
        MenuHandler menu = new MenuHandler();
        menu.start();
    }

    static void verifyUser() {
        boolean validInput = false;

        do {
            System.out.println("Are you an existing user? y or n");
            Scanner welcomeInput = new Scanner(System.in);
            String response = welcomeInput.next();

            if (response.trim().compareToIgnoreCase("y") == 0) {
                validInput = true;
            } else if (response.trim().compareToIgnoreCase("n") == 0) {
                validInput = true;
            } else {
                System.out.println("Invalid answer.");
            }

        } while (!validInput);

    }
}
