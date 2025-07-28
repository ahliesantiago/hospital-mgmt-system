package com.hospitalname.service.patient;

import com.hospitalname.model.Patient;
import com.hospitalname.repository.Repository;
import com.hospitalname.repository.patient.JsonPatientRepository;
import com.hospitalname.service.util.InputHandler;
import com.hospitalname.service.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class PatientInputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final Repository<Patient> repo = new JsonPatientRepository();

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

        System.out.println("Recap: " + firstName + " " + surname + " - " + sexText + "patient born on " + birthday + ".");
        boolean confirmation = InputValidator.validateYorN("Please confirm if the information above is correct to proceed with addition of the patient to the hospital's records: ");
        if (!confirmation) {
            System.out.println("Aborting...");
            return;
        }

        addPatient(firstName, surname, birthday, sex);
    }

    public String promptForInput(String promptText, String type) {
        boolean isValid = false;
        String input = "";

        while (!isValid) {
            System.out.print(promptText);
            input = scanner.nextLine();

            if (type.compareToIgnoreCase("text") == 0) isValid = InputValidator.validateStringInput(input);
            if (type.compareToIgnoreCase("date") == 0) isValid = InputValidator.validateDateInput(input);
            if (type.compareToIgnoreCase("sex") == 0) isValid = InputValidator.validateSexInput(input);
        }

        return InputHandler.capitalizeFirstLetter(input);
    }

    public String generatePatientId() {
        List<Patient> patients = repo.findAll();
        int count = patients.size();

        return String.format("PT-%05d", count + 1);
    }

    public void addPatient(String firstName, String surname, String birthdayStr, String sex) {
        String newPxId = generatePatientId();

        Patient newPatient = new Patient(newPxId, surname, firstName, birthdayStr, sex);
        repo.save(newPatient);
        System.out.println("Patient saved: " + newPxId);

        System.out.println();
        boolean confirmation = InputValidator.validateYorN("Would you like to add another patient?");
        if (!confirmation) {
            return;
        }

        collectPatientInfo();
    }
}
