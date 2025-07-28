package com.hospitalname.model;

public class Department {
    private String name;

    public Department(){
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() { return name; };

    @Override
    public String toString() {
        return String.format(
                "Department Name: %s%n" +
                "===========",
                name
        );
    }
}
