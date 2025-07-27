package com.hospitalname.cli;
import java.util.List;
import java.util.Scanner;
import com.hospitalname.cli.HelpPrinter;
import com.hospitalname.service.PatientService;

public class MenuHandler {
    static final List<String> validMenuOptions = List.of(
            "overview",
            "list",
            "list departments",
            "list patients",
            "list doctors",
            "list doctor",
            "view",
            "view department",
            "view patient",
            "view doctor",
            "add patient",
            "help",
            "exit"
    );

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exited = false;

        do {
            System.out.println("What would you like to do today?");

            String userMenuRequest = scanner.nextLine();

            if (!validMenuOptions.contains(userMenuRequest)) {
                System.out.println("Error: No such command exists (enter 'help' for a list of available commands)");
            }

            if (userMenuRequest.compareToIgnoreCase("exit") == 0) {
                exited = true;
            }

            handleRequest(userMenuRequest);
        } while (!exited);
    }

    public void handleRequest(String userRequest) {
        HelpPrinter help = new HelpPrinter();
        PatientService pxService = new PatientService();

        if (userRequest.compareToIgnoreCase("help") == 0) {
            help.printHelp();
        }

        if (userRequest.compareToIgnoreCase("add patient") == 0) {
            pxService.collectPatientInfo();
        }
    }
}
