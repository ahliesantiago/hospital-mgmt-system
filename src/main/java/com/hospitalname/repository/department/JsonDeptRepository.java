package com.hospitalname.repository.department;
import com.hospitalname.model.Department;
import com.hospitalname.repository.JsonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class JsonDeptRepository extends JsonRepository<Department> {
    public JsonDeptRepository() {
        super("data/departments.json", new TypeReference<List<Department>>() {});
    }
}
