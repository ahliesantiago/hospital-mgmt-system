package com.hospitalname.model;

import com.hospitalname.service.doctor.DoctorQueryHandler;
import com.hospitalname.service.patient.PatientQueryHandler;

public class Appointment {
    private String patientId;
    private String doctorId;
    private String appointmentDate;
    private String chiefComplaint;

    private final DoctorQueryHandler doctor = new DoctorQueryHandler();
    private final PatientQueryHandler patient = new PatientQueryHandler();

    public Appointment() { }

    public Appointment(String patientId, String doctorId, String appointmentDate, String chiefComplaint) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.chiefComplaint = chiefComplaint;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    @Override
    public String toString() {
        return String.format(
                "Appointment Date: %s%n" +
                "Patient: %s (%s)%n" +
                "Doctor: %s (%s)%n" +
                "Chief Complaint: %s%n" +
                "===========",
                patient.getNameById(patientId), patientId, doctor.getNameById(doctorId), doctorId, appointmentDate, chiefComplaint
        );
    }
}
