package com.ads.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Appointment {
    private Long appointmentId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "h:mm a")
    private LocalTime time;

    private Patient patient;
    private Dentist dentist;
    private Surgery surgery;

    public Appointment() {
    }

    public Appointment(Long appointmentId, LocalDate date, LocalTime time, Patient patient, Dentist dentist, Surgery surgery) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.dentist = dentist;
        this.surgery = surgery;
    }

    // region Getters and Setters

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }

    // endregion Getters and Setters
}
