package com.hospitalname.service.appointment;

import com.hospitalname.model.Appointment;
import com.hospitalname.model.Department;
import com.hospitalname.repository.Repository;
import com.hospitalname.repository.appointment.JsonApptRepository;
import com.hospitalname.service.doctor.DoctorQueryHandler;
import com.hospitalname.service.patient.PatientQueryHandler;
import com.hospitalname.service.util.InputValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AppointmentService {
    private final Scanner scanner = new Scanner(System.in);
    private final Repository<Appointment> repo = new JsonApptRepository();
    List<Appointment> appointments = repo.findAll();
    private final DoctorQueryHandler doctor = new DoctorQueryHandler();
    private final PatientQueryHandler patient = new PatientQueryHandler();

    public void collectInput() {
        String patientId = promptForInput(
                "Please provide the ID of the patient this appointment is for: ",
                "patient"
        );
        String doctorId = promptForInput(
                "Please provide the ID of the doctor for this appointment: ",
                "doctor"
        );
        String appointmentDate = promptForInput(
                "Appointment date (use MM/DD/YYYY format): ",
                "date"
        );
        String chiefComplaint = promptForInput(
                "Patient's chief complaint: ",
                "text"
        );


        patientId = patient.padId(patientId, "PT");
        doctorId = doctor.padId(doctorId, "DR");

        System.out.println("Recap: Appointment requested for " + patient.getNameById(patientId) + " with " + doctor.getNameById(doctorId) + " on " + appointmentDate + " for '" + chiefComplaint + "'.");
        boolean confirmation = InputValidator.validateYorN("Please confirm if the information above is correct to proceed with scheduling the appointment:");
        if (!confirmation) {
            System.out.println("Aborting...");
            return;
        }

        addAppointment(patientId, doctorId, appointmentDate, chiefComplaint);
    }

    public String promptForInput(String promptText, String type) {
        boolean isValid = false;
        String input = "";

        while (!isValid) {
            System.out.print(promptText);
            input = scanner.nextLine();

            if (type.equalsIgnoreCase("patient")) {
                isValid = patient.recordExists(input, "patient", "PT");
            }
            if (type.equalsIgnoreCase("doctor")) {
                isValid = doctor.recordExists(input, "doctor", "DR");
            }
            if (type.compareToIgnoreCase("text") == 0) isValid = InputValidator.validateStringInput(input);
            if (type.compareToIgnoreCase("date") == 0) isValid = InputValidator.validateDateInput(input);
        }

        return input;
    }

    public void addAppointment(String patientId, String doctorId, String appointmentDate, String chiefComplaint) {
        Appointment newAppointment = new Appointment(patientId, doctorId, appointmentDate, chiefComplaint);
        repo.save(newAppointment);
        System.out.println("Appointment created for " + patient.getNameById(patientId) + " with " + doctor.getNameById(doctorId) + " on " + appointmentDate + ".");
        System.out.println();
        boolean confirmation = InputValidator.validateYorN("Would you like to create a new appointment?");
        if (!confirmation) {
            return;
        }

        collectInput();
    }

    public int countUpcomingAppointments() {
        int upcomingCount = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        for (Appointment appointment : appointments) {
            LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate(), formatter);
            LocalDate today = LocalDate.now();
            if (appointmentDate.isEqual(today) || appointmentDate.isAfter(today)) {
                upcomingCount ++;
            }
        }

        return upcomingCount;
    }

    public void listRecords() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}
