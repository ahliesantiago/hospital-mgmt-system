package com.hospitalname.model;

public class Doctor {
    private String id;
    private String surname;
    private String firstName;
    private String birthday;
    private String sex;
    private String department;

    public Doctor() {
    }

    public Doctor(String id, String surname, String firstName, String birthday, String sex, String department) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.birthday = birthday;
        this.sex = sex;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format(
                "Doctor ID: %s%n" +
                "Name: %s %s%n" +
                "Birthday: %s%n" +
                "Sex: %s%n" +
                "Department: %s%n" +
                "===========",
                id, firstName, surname, birthday, sex, department
        );
    }
}
