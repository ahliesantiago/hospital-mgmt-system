package com.hospitalname.service.department;

import com.hospitalname.model.Department;
import com.hospitalname.repository.department.JsonDeptRepository;
import com.hospitalname.repository.Repository;
import com.hospitalname.service.util.InputHandler;
import com.hospitalname.service.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class DepartmentService {
    private final Scanner scanner = new Scanner(System.in);
    private final Repository<Department> repo = new JsonDeptRepository();
    List<Department> departments = repo.findAll();

    public void collectInput() {
        String deptName = promptForInput("What is the name of the department you'd like to add?");

        boolean confirmation = InputValidator.validateYorN("Please confirm if you would like to create the " + deptName + " Department for this hospital: ");
        if (!confirmation) {
            System.out.println("Aborting...");
            return;
        }

        addDepartment(deptName);
    }

    public String promptForInput(String promptText) {
        boolean isValid = false;
        String input = "";

        while (!isValid) {
            System.out.print(promptText);
            input = scanner.nextLine();
            isValid = InputValidator.validateStringInput(input);
        }

        return InputHandler.capitalizeFirstLetter(input);
    }

    public void addDepartment(String deptName) {
        Department newDepartment = new Department(deptName);
        repo.save(newDepartment);
        System.out.println(deptName + " Department saved.");

        System.out.println();
        boolean confirmation = InputValidator.validateYorN("Would you like to create a new department?");
        if (!confirmation) {
            return;
        }

        collectInput();
    }

    public void listDepartments() {
        if (departments.isEmpty()) {
            System.out.println("No department records found.");
            return;
        }

        for (Department department : departments) {
            System.out.println(department);
        }
    }

    public int countDepartments() {
        return departments.size();
    }

    public void getDepartment(String[] input) {
        String deptName = "";
        boolean departmentFound = false;

        if (departments.isEmpty()) {
            System.out.println("No department records found.");
            return;
        }

        if (input.length < 3) {
            System.out.print("What is the department ID you would like to review? ");
            deptName = scanner.nextLine();
        } else {
            deptName = input[2];
        }

        for (Department department : departments) {
            if (department.getName().equalsIgnoreCase(deptName)) {
                System.out.println(department);
                departmentFound = true;
            }
        }

        if (!departmentFound) {
            System.out.println("Error: No department found with ID " + deptName);
        }
    }
}
