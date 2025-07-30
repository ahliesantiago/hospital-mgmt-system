package com.hospitalname.repository.doctor;
import com.hospitalname.model.Doctor;
import com.hospitalname.repository.JsonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class JsonDoctorRepository extends JsonRepository<Doctor> {
    public JsonDoctorRepository() {
        super("data/doctors.json", new TypeReference<List<Doctor>>() {});
    }
}
