package com.hospitalname.service.doctor;

import com.hospitalname.model.Doctor;
import com.hospitalname.repository.doctor.JsonDoctorRepository;
import com.hospitalname.service.QueryHandler;

public class DoctorQueryHandler extends QueryHandler<Doctor> {
    public DoctorQueryHandler() {
        super(new JsonDoctorRepository());
    }

    @Override
    protected String getId(Doctor doctor) {
        return doctor.getId();
    }
}
