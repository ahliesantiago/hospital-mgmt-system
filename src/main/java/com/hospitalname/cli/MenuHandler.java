package com.hospitalname.cli;
import java.util.List;
import java.util.Scanner;
import com.hospitalname.cli.HelpPrinter;
import com.hospitalname.service.PatientService;

public class MenuHandler {
    static final List<String> validMenuOptions = List.of(
            "list patients",
            "view patient",
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

            String[] parts = userMenuRequest.split("\\s+", 3);
            String baseCommand = parts.length >= 2
                    ? parts[0] + " " + parts[1]
                    : parts[0];
            if (!validMenuOptions.contains(baseCommand)) {
                System.out.println("Error: No such command exists (enter 'help' for a list of available commands)");
            }

            if (userMenuRequest.compareToIgnoreCase("exit") == 0) {
                exited = true;
            }

            handleRequest(userMenuRequest);
        } while (!exited);
    }

    public void handleRequest(String userCommand) {
        HelpPrinter help = new HelpPrinter();
        PatientService pxService = new PatientService();
        String[] parts = userCommand.split("\\s+", 3);
        String baseCommand = parts.length >= 2
                ? parts[0] + " " + parts[1]
                : parts[0];

        switch (baseCommand) {
            case "help":
                help.printHelp();

            case "add patient":
                pxService.collectPatientInfo();

            case "list patients":
                pxService.listPatients();

            case "view patient":
                pxService.getPatient(parts);
        }
    }
}
