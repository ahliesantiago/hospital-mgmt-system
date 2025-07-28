package com.hospitalname.service.patient;

import com.hospitalname.model.Patient;
import com.hospitalname.repository.JsonPatientRepository;
import com.hospitalname.repository.PatientRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class PatientInputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final PatientRepository repo = new JsonPatientRepository();

    public void collectPatientInfo() {
        System.out.println("Please provide the patient's information:");

        String firstName = promptForInput(
                "Patient's first name: ",
                "text"
        );

        String surname = promptForInput(
                "Patient's last name: ",
                "text"
        );

        String birthday = promptForInput(
                "Patient's birthday (use MM/DD/YYYY format): ",
                "date"
        );

        String sex = promptForInput(
                "Patient's biological sex (male/female/other): ",
                "sex"
        );

        String sexText = "";
        if (sex.compareToIgnoreCase("other") == 0) {
            sexText = "";
        } else {
            sexText = sex + " ";
        }

        System.out.println("Please confirm with y/n if the information is correct and to add the patient to the hospital's records: ");
        System.out.println(firstName + " " + surname + " - " + sexText + "patient born on " + birthday + ".");

        String confirmation = scanner.nextLine();
        if (confirmation.compareToIgnoreCase("y") == 0) {
            addPatient(firstName, surname, birthday, sex);
        } else if (confirmation.compareToIgnoreCase("n") == 0) {
            System.out.println("Aborting...");
        }
    }

    public String promptForInput(String promptText, String type) {
        boolean isValid = false;
        String input = "";

        while (!isValid) {
            System.out.print(promptText);
            input = scanner.nextLine();

            if (type.compareToIgnoreCase("text") == 0) isValid = validateStringInput(input);
            if (type.compareToIgnoreCase("date") == 0) isValid = validateDateInput(input);
            if (type.compareToIgnoreCase("sex") == 0) isValid = validateSexInput(input);
        }

        return capitalizeFirstLetter(input);
    }

    /*
    Validate user input strings, specifically ensuring that the input
    contains only letters (i.e. no symbols or numbers).
    */
    public boolean validateStringInput(String input) {
        if (input.matches("[a-zA-Z]+")) {
            return true;
        } else {
            System.out.println("Invalid input. Only letters are allowed.");
            return false;
        }
    }

    public boolean validateDateInput(String input) {
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

    public boolean validateSexInput(String input) {
        List<String> sexes = List.of("male", "female", "other");
        return sexes.contains(input);
    }

    public String setPatientId() {
        List<Patient> patients = repo.findAll();
        int count = patients.size();

        return String.format("PT-%05d", count + 1);
    }

    // Capitalize first letter of a string
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) return null;

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public void addPatient(String firstName, String surname, String birthdayStr, String sex) {
        String newPxId = setPatientId();

        Patient newPatient = new Patient(newPxId, surname, firstName, birthdayStr, sex);
        repo.save(newPatient);
        System.out.println("Patient saved: " + newPxId);
    }
}
