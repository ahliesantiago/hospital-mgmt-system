package com.hospitalname.service;
import com.hospitalname.service.department.DepartmentService;
import com.hospitalname.service.doctor.DoctorQueryHandler;
import com.hospitalname.service.patient.PatientQueryHandler;

public class OverallService {
    DepartmentService departmentService = new DepartmentService();
    DoctorQueryHandler doctorQuery = new DoctorQueryHandler();
    PatientQueryHandler patientQuery = new PatientQueryHandler();

    public void getOverallInfo() {
        System.out.println("Departments: " + departmentService.countDepartments());
        System.out.println("Doctors: " + doctorQuery.countRecords());
        System.out.println("Patients: " + patientQuery.countRecords());
        System.out.println("Upcoming Appointments: " + 0);
    }
}
