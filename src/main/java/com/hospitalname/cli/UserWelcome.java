package com.hospitalname.cli;
import com.hospitalname.service.util.InputValidator;
import java.util.Scanner;

public class UserWelcome {
    public void start() {
        System.out.println("Welcome!");

        // verifyUser();
        MenuHandler menu = new MenuHandler();
        menu.start();
    }

    static boolean verifyUser() {
        return InputValidator.validateYorN("Are you an existing user? y or n");
    }
}
