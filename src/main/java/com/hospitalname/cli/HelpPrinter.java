package com.hospitalname.cli;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelpPrinter {
    private static final Map<String, String> COMMANDS;

    static {
        Map<String, String> commands = new LinkedHashMap<>();
        commands.put("overview", "View a summary of the hospital records");
        commands.put("add <type>", "Add a department, doctor, patient record, or appointment");
        commands.put("list <type>", "Display a list of your specified hospital data (departments, doctors, patients, appointments) and a summary of their information");
        commands.put("view <type> <id>", "Display complete information of specific hospital data based on your provided ID");
        commands.put("view patient <id>", "Display complete information of a specific patient");
        commands.put("view doctor <id>", "Display complete information of a specific doctor");
        commands.put("list doctor <id> patients", "Display list of patients of a specific doctor");
        commands.put("view department <name>", "Display complete information of a specific department");
        commands.put("help", "Display this help message");
        commands.put("exit", "Exits the application");
        COMMANDS = Collections.unmodifiableMap(commands);
    }

    public void printHelp() {
        System.out.println("List of commands:");
        COMMANDS.forEach((cmd, desc) -> System.out.printf("  %-25s - %s%n", cmd, desc));
    }
}
