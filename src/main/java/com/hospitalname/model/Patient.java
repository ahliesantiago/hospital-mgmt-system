package com.hospitalname.model;

public class Patient {
    private String id;
    private String surname;
    private String firstName;
    private String birthday;
    private String sex;

    public Patient(String surname, String firstName, String birthday, String sex) {
        this.surname = surname;
        this.firstName = firstName;
        this.birthday = birthday;
        this.sex = sex;
    }
}
