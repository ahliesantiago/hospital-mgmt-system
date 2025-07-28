package com.hospitalname.repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonRepository<T> implements Repository<T> {
    protected final ObjectMapper mapper = new ObjectMapper();
    protected final TypeReference<List<T>> typeRef;
    protected final File file;

    protected JsonRepository(String filePath, TypeReference<List<T>> typeRef) {
        this.file = new File(filePath);
        this.typeRef = typeRef;
        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error initializing patient file", e);
        }
    }

    @Override
    public void save(T obj) {
        List<T> data = findAll();
        data.add(obj);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> findAll() {
        if (!file.exists() || file.length() == 0) return new ArrayList<>();
        try {
            return mapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
