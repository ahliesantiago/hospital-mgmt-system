package com.hospitalname.service.patient;

import com.hospitalname.model.Patient;
import com.hospitalname.repository.patient.JsonPatientRepository;
import com.hospitalname.repository.patient.PatientRepository;

import java.util.List;
import java.util.Scanner;

public class PatientQueryHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final PatientRepository repo = new JsonPatientRepository();
    List<Patient> patients = repo.findAll();

    public void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patient records found.");
            return;
        }

        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    public void getPatient(String[] input) {
        String patientId = "";
        boolean patientFound = false;

        if (patients.isEmpty()) {
            System.out.println("No patient records found.");
            return;
        }

        if (input.length < 3) {
            System.out.print("What is the patient ID you would like to review? ");
            patientId = scanner.nextLine();
        } else {
            patientId = input[2];
        }

        if (!patientId.startsWith("PT")) {
            String padded = String.format("%1$" + 5 + "s", patientId).replace(' ', '0');
            patientId = "PT-" + padded;
        }

        for (Patient patient : patients) {
            if (patient.getId().compareToIgnoreCase(patientId) == 0) {
                System.out.println(patient);
                patientFound = true;
            }
        }

        if (!patientFound) {
            System.out.println("Error: No patient found with ID " + patientId);
        }
    }

    public int countPatients() {
        return patients.size();
    }

    public void computeAge(String birthdayStr) {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        // LocalDate birthday = LocalDate.parse(birthdayStr, formatter);
    }
}
