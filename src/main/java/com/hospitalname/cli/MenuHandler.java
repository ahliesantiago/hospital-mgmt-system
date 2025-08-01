package com.hospitalname.cli;
import java.util.Scanner;
import com.hospitalname.service.doctor.DoctorInputHandler;
import com.hospitalname.service.doctor.DoctorQueryHandler;
import com.hospitalname.service.patient.PatientInputHandler;
import com.hospitalname.service.patient.PatientQueryHandler;
import com.hospitalname.service.department.DepartmentService;
import com.hospitalname.service.appointment.AppointmentService;
import com.hospitalname.service.OverallService;

public class MenuHandler {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exited = false;

        do {
            System.out.printf("%nWhat would you like to do?%n");
            String userMenuRequest = scanner.nextLine();

            if (userMenuRequest.compareToIgnoreCase("exit") == 0) {
                exited = true;
            }

            handleRequest(userMenuRequest);
        } while (!exited);
    }

    public void handleRequest(String userCommand) {
        HelpPrinter help = new HelpPrinter();
        DoctorInputHandler doctorInputService = new DoctorInputHandler();
        DoctorQueryHandler drQueryService = new DoctorQueryHandler();
        PatientInputHandler pxInputService = new PatientInputHandler();
        PatientQueryHandler pxQueryService = new PatientQueryHandler();
        OverallService overview = new OverallService();
        AppointmentService appointment = new AppointmentService();
        DepartmentService department = new DepartmentService();

        String[] parts = userCommand.split("\\s+", 3);
        String baseCommand = parts.length >= 2
                ? parts[0] + " " + parts[1]
                : parts[0];

        switch (baseCommand) {
            case "help":
                help.printHelp();
                break;

            case "overview":
                overview.getOverallInfo();
                break;

            case "add department":
                department.collectInput(parts);
                break;

            case "list departments":
                department.listDepartments();
                break;

            case "view department":
                department.getDepartment(parts);
                break;

            case "add doctor":
                doctorInputService.collectInfo();
                break;

            case "list doctors":
                drQueryService.listRecords();
                break;

            case "view doctor":
                drQueryService.getRecordById(parts, "doctor", "DR");
                break;

            case "add patient":
                pxInputService.collectPatientInfo();
                break;

            case "list patients":
                pxQueryService.listRecords();
                break;

            case "view patient":
                pxQueryService.getRecordById(parts, "patient", "PT");
                break;

            case "add appointment":
                appointment.collectInput();
                break;

            case "list appointments":
                appointment.listRecords();
                break;

            case "exit":
                break;

            default:
                System.out.println("Error: No such command exists (type 'help' to see a list of valid options)");
                break;
        }
    }
}
