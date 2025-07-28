package com.hospitalname.model;

public class Patient {
    private String id;
    private String surname;
    private String firstName;
    private String birthday;
    private String sex;

    public Patient() {
    }

    public Patient(String id, String surname, String firstName, String birthday, String sex) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.birthday = birthday;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s%n" +
                "Name: %s %s%n" +
                "Birthday: %s%n" +
                "Sex: %s%n" +
                "===========",
                id, firstName, surname, birthday, sex
        );
    }
}
