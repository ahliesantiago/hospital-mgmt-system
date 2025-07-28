package com.hospitalname.repository;
import java.util.List;

public interface Repository<T> {
    void save (T obj);
    List<T> findAll();
}
