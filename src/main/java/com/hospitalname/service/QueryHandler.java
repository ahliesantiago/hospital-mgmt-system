package com.hospitalname.service;

import com.hospitalname.model.Department;
import com.hospitalname.repository.Repository;

import java.util.List;
import java.util.Scanner;

public class QueryHandler<T> {
    private final Scanner scanner = new Scanner(System.in);
    protected final Repository<T> repo;
    protected List<T> records;

    public QueryHandler(Repository<T> repo) {
        this.repo = repo;
        this.records = repo.findAll();
    }

    public void listRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (T record : records) {
            System.out.println(record);
        }
    }

    public void getRecordById(String[] input, String type, String prefix) {
        if (records.isEmpty()) {
            System.out.println("No " + type + " records found.");
            return;
        }

        String id;
        if (input.length < 3) {
            System.out.print("What is the " + type + " ID you would like to review? ");
            id = scanner.nextLine();
        } else {
            id = input[2];
        }

        id = padId(id, prefix);

        boolean recordFound = false;
        for (T record : records) {
            String recordId = getId(record);
            if (recordId != null && recordId.equalsIgnoreCase(id)) {
                System.out.println(record);
                recordFound = true;
            }
        }

        if (!recordFound) {
            System.out.println("Error: No " + type + " record found with ID " + id);
        }
    }

    protected String getId(T record) {
        return null;
    }

    protected String getName(T record) {
        return null;
    }

    public String padId(String id, String prefix) {
        if (!id.startsWith(prefix)) {
            String padded = String.format("%1$" + 5 + "s", id).replace(' ', '0');
            id = prefix + "-" + padded;
        }

        return id;
    }

    public boolean recordExists(String id, String type, String prefix) {
        id = padId(id, prefix);

        for (T record : records) {
            String recordId = getId(record);
            if (recordId != null && recordId.equalsIgnoreCase(id)) {
                return true;
            }
        }

        System.out.println("Error: No " + type + " record found with ID " + id);
        return false;
    }

    public String getNameById(String id) {
        for (T record : records) {
            String recordId = getId(record);
            if (recordId != null && recordId.equalsIgnoreCase(id)) {
                return getName(record);
            }
        }

        return "Error";
    }

    public int countRecords() {
        return records.size();
    }
}
