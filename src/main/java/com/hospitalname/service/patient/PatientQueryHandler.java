package com.hospitalname.service.patient;

import com.hospitalname.model.Patient;
import com.hospitalname.repository.patient.JsonPatientRepository;
import com.hospitalname.service.QueryHandler;

public class PatientQueryHandler extends QueryHandler<Patient> {

    public PatientQueryHandler() {
        super(new JsonPatientRepository());
    }

    @Override
    protected String getId(Patient patient) {
        return patient.getId();
    }
}
