package com.hospitalname.service;
import com.hospitalname.service.department.DepartmentService;
import com.hospitalname.service.patient.PatientQueryHandler;

public class OverallService {
    DepartmentService departmentService = new DepartmentService();
    PatientQueryHandler patientQuery = new PatientQueryHandler();

    public void getOverallInfo() {
        System.out.println("Departments: " + departmentService.countDepartments());
        System.out.println("Patients: " + patientQuery.countPatients());
    }
}
