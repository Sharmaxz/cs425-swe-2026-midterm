package com.ads.repository;

import com.ads.domain.Appointment;
import com.ads.domain.Dentist;
import com.ads.domain.Patient;
import com.ads.domain.Surgery;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AppointmentRepository {
    private List<Appointment> appointments;

    public AppointmentRepository() {
        appointments = new ArrayList<>();
        loadInitialData();
    }

    private void loadInitialData() {
        Dentist defaultDentist = new Dentist(1L, "Tony", "Smith", "General Dentistry");
        Surgery defaultSurgery = new Surgery(1L, "Downtown ADS", "123 Main St, Iowa City, IA 52240", "555-1234");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.US);

        appointments.add(new Appointment(
                1L,
                LocalDate.of(2026, 2, 28),
                LocalTime.parse("10:05 AM", timeFormatter),
                new Patient(1L, "John", "Smith", "(641) 001-1234", LocalDate.of(1987, 1, 19)),
                defaultDentist,
                defaultSurgery
        ));

        appointments.add(new Appointment(
                2L,
                LocalDate.of(2025, 12, 31),
                LocalTime.parse("1:45 PM", timeFormatter),
                new Patient(2L, "Anna", "Jones", "(319) 716-1987", LocalDate.of(2001, 7, 26)),
                defaultDentist,
                defaultSurgery
        ));

        appointments.add(new Appointment(
                3L,
                LocalDate.of(2027, 5, 4),
                LocalTime.parse("2:00 PM", timeFormatter),
                new Patient(3L, "Carlos", "Jimenez", "(319) 098-7711", LocalDate.of(1969, 11, 5)),
                defaultDentist,
                defaultSurgery
        ));

        appointments.add(new Appointment(
                4L,
                LocalDate.of(2026, 9, 16),
                LocalTime.parse("11:15 AM", timeFormatter),
                new Patient(4L, "Albert", "Einstein", "(641) 119-6142", LocalDate.of(1955, 12, 28)),
                defaultDentist,
                defaultSurgery
        ));
    }

    public List<Appointment> findAll() {
        return appointments;
    }

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }
}
