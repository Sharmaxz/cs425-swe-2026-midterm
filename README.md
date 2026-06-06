# ADS Dental Appointments Application

Command-Line Interface (CLI) application for the **Adventist Dental Surgeries (ADS)** Dental Appointment Management system, implemented in Java with Maven build automation.

## Runtime Requirements

- **Java 21** (LTS) or newer is required to run the application.
- **Maven 3.9+** is required to build from source.
- **Docker** is optional, for running the containerized version.

## How to Build and Run Locally

1. Open a terminal in the project root directory.

2. Build the executable JAR using Maven:
   ```bash
   mvn clean package
   ```

3. Run the application (displays all appointments and quarterly upcoming appointments):
   ```bash
   java -jar target/adventist-dental-surgeries.jar
   ```

### CLI Commands

| Command | Description |
|---|---|
| `java -jar target/adventist-dental-surgeries.jar` | Run all reports (default) |
| `java -jar target/adventist-dental-surgeries.jar all` | Show all appointments (descending) |
| `java -jar target/adventist-dental-surgeries.jar quarterly` | Show quarterly upcoming appointments |
| `java -jar target/adventist-dental-surgeries.jar quarterly yyyy-MM-dd` | Quarterly with a specific reference date |
| `java -jar target/adventist-dental-surgeries.jar help` | Show usage help |

## GitHub Repository

**Source Code:** https://github.com/Sharmaxz/cs425-swe-2026-midterm

## Docker Container

**Docker Hub Repository:** https://hub.docker.com/r/sharmaxz/cs425-swe-2026-midterm

### Pull and Run from Docker Hub
```bash
docker pull sharmaxz/cs425-swe-2026-midterm:latest
docker run --rm sharmaxz/cs425-swe-2026-midterm:latest
```

### Build Locally
```bash
docker build -t sharmaxz/cs425-swe-2026-midterm:latest .
docker run --rm sharmaxz/cs425-swe-2026-midterm:latest
```

### Push to Docker Hub
```bash
docker login
docker push sharmaxz/cs425-swe-2026-midterm:latest
```

## Project Structure

```
src/
└── main/
    └── java/
        └── com/ads/
            ├── AdventistDentalSurgeries.java   # Main entry point (App UI layer)
            ├── domain/                          # Domain model classes
            │   ├── Appointment.java
            │   ├── Bill.java
            │   ├── Dentist.java
            │   ├── Patient.java
            │   └── Surgery.java
            ├── repository/                      # Data access layer
            │   └── AppointmentRepository.java
            └── service/                         # Business logic / service layer
                └── AppointmentService.java
```
