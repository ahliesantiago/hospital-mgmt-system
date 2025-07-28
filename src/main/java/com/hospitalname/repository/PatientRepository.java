package com.hospitalname.repository;
import java.util.List;
import com.hospitalname.model.Patient;

public interface PatientRepository {
    void save (Patient patient);
    List<Patient> findAll();
}
