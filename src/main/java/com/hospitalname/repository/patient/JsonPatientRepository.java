package com.hospitalname.repository.patient;
import com.hospitalname.model.Patient;
import com.hospitalname.repository.JsonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class JsonPatientRepository extends JsonRepository<Patient> {
    public JsonPatientRepository() {
        super("data/patients.json", new TypeReference<List<Patient>>() {});
    }
}
