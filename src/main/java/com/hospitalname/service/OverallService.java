package com.hospitalname.service;
import com.hospitalname.service.patient.PatientQueryHandler;

public class OverallService {
    PatientQueryHandler patientQuery = new PatientQueryHandler();

    public void getOverallInfo() {
        System.out.println("Patients: " + patientQuery.countPatients());
    }
}
