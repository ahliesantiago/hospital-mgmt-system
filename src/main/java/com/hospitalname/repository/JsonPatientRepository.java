package com.hospitalname.repository;
import com.hospitalname.model.Patient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPatientRepository implements PatientRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String FILE_PATH = "data/patients.json";
    private final File file = new File(FILE_PATH);

    public JsonPatientRepository() {
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error initializing patient file", e);
        }
    }

    @Override
    public void save(Patient patient) {
        List<Patient> patients = findAll();
        patients.add(patient);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> findAll() {
        if (!file.exists() || file.length() == 0) return new ArrayList<>();
        try {
            return mapper.readValue(file, new TypeReference<List<Patient>>() {});
        } catch (IOException e) {
             e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
