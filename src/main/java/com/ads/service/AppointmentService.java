package com.ads.service;

import com.ads.domain.Appointment;
import com.ads.repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> getAllAppointmentsSortedDesc() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Appointment::getDate)
                        .thenComparing(Appointment::getTime).reversed())
                .collect(Collectors.toList());
    }

    public List<Appointment> getQuarterlyUpcomingAppointments(LocalDate currentDate) {
        int currentQuarter = (currentDate.getMonthValue() - 1) / 3 + 1;
        int nextQuarter = currentQuarter == 4 ? 1 : currentQuarter + 1;
        int targetYear = currentQuarter == 4 ? currentDate.getYear() + 1 : currentDate.getYear();

        int startMonth = (nextQuarter - 1) * 3 + 1;
        int endMonth = startMonth + 2;

        LocalDate nextQuarterStart = LocalDate.of(targetYear, Month.of(startMonth), 1);
        LocalDate nextQuarterEnd = LocalDate.of(targetYear, Month.of(endMonth),
                Month.of(endMonth).length(LocalDate.of(targetYear, 1, 1).isLeapYear()));

        return repository.findAll().stream()
                .filter(a -> !a.getDate().isBefore(nextQuarterStart) && !a.getDate().isAfter(nextQuarterEnd))
                .sorted(Comparator.comparing(Appointment::getDate)
                        .thenComparing(Appointment::getTime))
                .collect(Collectors.toList());
    }

    public String getNextQuarterDescription(LocalDate currentDate) {
        int currentQuarter = (currentDate.getMonthValue() - 1) / 3 + 1;
        int nextQuarter = currentQuarter == 4 ? 1 : currentQuarter + 1;
        int targetYear = currentQuarter == 4 ? currentDate.getYear() + 1 : currentDate.getYear();
        return "Q" + nextQuarter + " " + targetYear;
    }

    public void bookAppointment(Appointment appointment) {
        // Business rule: patient cannot book if they have an unpaid bill older than 12 months
        if (appointment.getPatient().hasOverdueUnpaidBill(LocalDate.now())) {
            throw new IllegalStateException("Patient has an outstanding unpaid bill exceeding 12 months.");
        }

        // Business rule: dentist cannot have more than 5 appointments in any given week
        java.time.temporal.WeekFields weekFields = java.time.temporal.WeekFields.of(java.util.Locale.getDefault());
        int targetWeek = appointment.getDate().get(weekFields.weekOfWeekBasedYear());
        int targetYear = appointment.getDate().get(weekFields.weekBasedYear());

        long currentWeekCount = repository.findAll().stream()
                .filter(a -> a.getDentist().equals(appointment.getDentist()))
                .filter(a -> a.getDate().get(weekFields.weekOfWeekBasedYear()) == targetWeek
                          && a.getDate().get(weekFields.weekBasedYear()) == targetYear)
                .count();

        if (currentWeekCount >= 5) {
            throw new IllegalStateException("Dentist cannot have more than 5 appointments in a given week.");
        }

        repository.save(appointment);
    }
}
