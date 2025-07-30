package com.hospitalname.repository.appointment;
import com.hospitalname.model.Appointment;
import com.hospitalname.repository.JsonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class JsonApptRepository extends JsonRepository<Appointment> {
    public JsonApptRepository() {
        super("data/appointments.json", new TypeReference<List<Appointment>>() {});
    }
}
