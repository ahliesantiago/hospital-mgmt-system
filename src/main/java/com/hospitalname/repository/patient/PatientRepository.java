package com.hospitalname.repository.patient;
import java.util.List;
import com.hospitalname.model.Patient;

public interface PatientRepository {
    void save (Patient patient);
    List<Patient> findAll();
}
