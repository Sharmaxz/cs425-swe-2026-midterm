package com.ads.domain;

import java.util.ArrayList;
import java.util.List;

public class Dentist {
    private Long dentistId;
    private String firstName;
    private String lastName;
    private String specialization;

    private List<Appointment> appointments = new ArrayList<>();

    public Dentist() {
    }

    public Dentist(Long dentistId, String firstName, String lastName, String specialization) {
        this.dentistId = dentistId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // region Getters and Setters

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dentist dentist = (Dentist) o;
        return java.util.Objects.equals(firstName, dentist.firstName) &&
                java.util.Objects.equals(lastName, dentist.lastName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastName);
    }

    // endregion Getters and Setters

}
