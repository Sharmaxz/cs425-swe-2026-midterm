package com.ads;

import com.ads.domain.Appointment;
import com.ads.repository.AppointmentRepository;
import com.ads.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AdventistDentalSurgeries {
    private static final LocalDate DEFAULT_CURRENT_DATE = LocalDate.of(2026, 6, 6);

    private final AppointmentService appointmentService;
    private final ObjectMapper objectMapper;

    public AdventistDentalSurgeries(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
        this.objectMapper = createObjectMapper();
    }

    public static void main(String[] args) {
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        AdventistDentalSurgeries application = new AdventistDentalSurgeries(appointmentService);
        application.start(args);
    }

    public void start(String[] args) {
        try {
            if (args == null || args.length == 0) {
                displayAllAppointments();
                System.out.println();
                displayQuarterlyUpcomingAppointments(DEFAULT_CURRENT_DATE);
                return;
            }

            String command = args[0].toLowerCase();

            switch (command) {
                case "all" -> displayAllAppointments();

                case "quarterly" -> {
                    LocalDate currentDate = DEFAULT_CURRENT_DATE;

                    if (args.length >= 2) {
                        currentDate = parseDate(args[1]);
                    }

                    displayQuarterlyUpcomingAppointments(currentDate);
                }

                case "help", "--help", "-h" -> displayHelp();

                default -> {
                    System.out.println("Unknown command: " + args[0]);
                    System.out.println();
                    displayHelp();
                }
            }

        } catch (Exception exception) {
            System.err.println("An error occurred while running the Adventist Dental Surgeries application.");
            System.err.println("Error: " + exception.getMessage());
        }
    }

    private void displayAllAppointments() throws Exception {
        System.out.println("=======================================================");
        System.out.println("Adventist Dental Surgeries - ADS");
        System.out.println("4.2.1 All Appointments");
        System.out.println("Sorted by Appointment Date and Time in Descending Order");
        System.out.println("=======================================================");

        List<Appointment> appointments = appointmentService.getAllAppointmentsSortedDesc();

        String json = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(appointments);

        System.out.println(json);
    }

    private void displayQuarterlyUpcomingAppointments(LocalDate currentDate) throws Exception {
        System.out.println("=======================================================");
        System.out.println("Adventist Dental Surgeries - ADS");
        System.out.println("4.2.2 Quarterly Upcoming Appointments");
        System.out.println("Current Date: " + currentDate);
        System.out.println("Next Quarter: " + appointmentService.getNextQuarterDescription(currentDate));
        System.out.println("Sorted by Appointment Date and Time in Ascending Order");
        System.out.println("=======================================================");

        List<Appointment> appointments = appointmentService.getQuarterlyUpcomingAppointments(currentDate);

        String json = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(appointments);

        System.out.println(json);
    }

    private void displayHelp() {
        System.out.println("Adventist Dental Surgeries - ADS CLI");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  java -jar target/adventist-dental-surgeries.jar");
        System.out.println("  java -jar target/adventist-dental-surgeries.jar all");
        System.out.println("  java -jar target/adventist-dental-surgeries.jar quarterly");
        System.out.println("  java -jar target/adventist-dental-surgeries.jar quarterly yyyy-MM-dd");
        System.out.println("  java -jar target/adventist-dental-surgeries.jar help");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java -jar target/adventist-dental-surgeries.jar all");
        System.out.println("  java -jar target/adventist-dental-surgeries.jar quarterly 2026-06-06");
    }

    private LocalDate parseDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(
                    "Invalid date format. Please use yyyy-MM-dd. Example: 2026-06-06"
            );
        }
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}