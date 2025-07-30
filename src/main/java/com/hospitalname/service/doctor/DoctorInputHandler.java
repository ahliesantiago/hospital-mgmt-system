package com.hospitalname.service.doctor;

import com.hospitalname.model.Doctor;
import com.hospitalname.repository.Repository;
import com.hospitalname.repository.doctor.JsonDoctorRepository;
import com.hospitalname.service.department.DepartmentService;
import com.hospitalname.service.util.InputHandler;
import com.hospitalname.service.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class DoctorInputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final Repository<Doctor> repo = new JsonDoctorRepository();
    private final DepartmentService deptService = new DepartmentService();

    public void collectInfo() {
        System.out.println("Please provide the doctor's information:");

        String firstName = promptForInput(
                "Doctor's first name: ",
                "text"
        );

        String surname = promptForInput(
                "Doctor's last name: ",
                "text"
        );

        String birthday = promptForInput(
                "Doctor's birthday (use MM/DD/YYYY format): ",
                "date"
        );

        String sex = promptForInput(
                "Doctor's biological sex (male/female/other): ",
                "sex"
        );

        String department = promptForInput(
                "Doctor's department (enter 'none' if not applicable): ",
                "deptText"
        );

        String sexText = "";
        if (sex.equalsIgnoreCase("other")) {
            sexText = "";
        } else {
            sexText = sex + " ";
        }

        String deptText = "";
        if (department.equalsIgnoreCase("none")) {
            deptText = "currently not part of any specific department.";
        } else {
            deptText = "part of the " + department + " Department.";
        }

        System.out.println("Recap: Dr. " + firstName + " " + surname + " - " + sexText + "doctor, born on " + birthday + ", who is " + deptText);
        boolean confirmation = InputValidator.validateYorN("Please confirm if the information above is correct to proceed with addition of the doctor to the hospital's records: ");
        if (!confirmation) {
            System.out.println("Aborting...");
            return;
        }

        addDoctor(firstName, surname, birthday, sex, department);
    }

    public String promptForInput(String promptText, String type) {
        boolean isValid = false;
        String input = "";

        while (!isValid) {
            System.out.print(promptText);
            input = scanner.nextLine();

            if (type.equalsIgnoreCase("text")) isValid = InputValidator.validateStringInput(input);
            if (type.equalsIgnoreCase("date")) isValid = InputValidator.validateDateInput(input);
            if (type.equalsIgnoreCase("sex")) isValid = InputValidator.validateSexInput(input);
            if (type.equalsIgnoreCase("deptText")) {
                isValid = InputValidator.validateStringInput(input) &&
                        deptService.checkExistenceByName(input);
            }
        }

        return InputHandler.capitalizeFirstLetter(input);
    }

    public String generateDoctorId() {
        List<Doctor> doctors = repo.findAll();
        int count = doctors.size();

        return String.format("DR-%05d", count + 1);
    }

    public void addDoctor(String firstName, String surname, String birthdayStr, String sex, String department) {
        String newPxId = generateDoctorId();

        Doctor newDoctor = new Doctor(newPxId, surname, firstName, birthdayStr, sex, department);
        repo.save(newDoctor);
        System.out.println("Doctor saved: " + newPxId);

        System.out.println();
        boolean confirmation = InputValidator.validateYorN("Would you like to add another doctor?");
        if (!confirmation) {
            return;
        }

        collectInfo();
    }
}
