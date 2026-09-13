package DesignPattern.meditrack;

import DesignPattern.meditrack.constants.Specialization;
import DesignPattern.meditrack.entity.Doctor;
import DesignPattern.meditrack.entity.Patient;
import DesignPattern.meditrack.entity.TimeSlot;
import DesignPattern.meditrack.exception.InvalidDataException;
import DesignPattern.meditrack.exception.ResourceNotFoundException;
import DesignPattern.meditrack.service.DoctorService;
import DesignPattern.meditrack.service.PatientService;
import DesignPattern.meditrack.util.IdGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeditrackApplication {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DoctorService doctorService = new DoctorService();
    private static final PatientService patientService = new PatientService();

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("        Welcome to MediTrack");
        System.out.println("====================================");

        boolean running = true;
        while (running) {
            try {
                running = handleRoleSelection();
            } catch (InvalidDataException | ResourceNotFoundException e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nUnexpected error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("\nThank you for using MediTrack.");
    }

    private static boolean handleRoleSelection()
            throws InvalidDataException, ResourceNotFoundException {

        System.out.println("\n--- Select your role ---");
        System.out.println("1. Doctor");
        System.out.println("2. Patient");
        System.out.println("0. Exit");

        int choice = readInt("Enter choice: ");
        switch (choice) {
            case 1:
                runDoctorMenu();
                return true;
            case 2:
                runPatientMenu();
                return true;
            case 0:
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
                return true;
        }
    }

    private static void runDoctorMenu()
            throws InvalidDataException, ResourceNotFoundException {

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Doctor ---");
            System.out.println("1. Register");
            System.out.println("2. View all doctors");
            System.out.println("3. Search by ID");
            System.out.println("4. Search by name");
            System.out.println("5. Update");
            System.out.println("6. Delete");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    registerDoctor();
                    break;
                case 2:
                    listDoctors(doctorService.getDoctors());
                    break;
                case 3:
                    searchDoctorById();
                    break;
                case 4:
                    searchDoctorsByName();
                    break;
                case 5:
                    updateDoctor();
                    break;
                case 6:
                    deleteDoctor();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void runPatientMenu()
            throws InvalidDataException, ResourceNotFoundException {

        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\n--- Patient ---");
            System.out.println("1. Register");
            System.out.println("2. View all patients");
            System.out.println("3. Search by ID");
            System.out.println("4. Search by name");
            System.out.println("5. Update");
            System.out.println("6. Delete");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    listPatients(patientService.getPatients());
                    break;
                case 3:
                    searchPatientById();
                    break;
                case 4:
                    searchPatientsByName();
                    break;
                case 5:
                    updatePatient();
                    break;
                case 6:
                    deletePatient();
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerDoctor() throws InvalidDataException {
        System.out.println("\n--- Register Doctor ---");
        Doctor doctor = new Doctor(
                IdGenerator.getNewDoctorId(),
                readString("Name: "),
                readInt("Age: "),
                readString("Phone: "),
                readString("Email: "),
                readSpecialization(),
                readDouble("Consultation Fee: "),
                readAvailableSlots()
        );
        doctorService.createDoctor(doctor);
        doctor.displayInfo();
    }

    private static void searchDoctorById() throws ResourceNotFoundException {
        Doctor doctor = doctorService.getDoctorById(readInt("Doctor ID: "));
        doctor.displayInfo();
    }

    private static void searchDoctorsByName() {
        listDoctors(doctorService.getDoctorsByNameMatches(readString("Name: ")));
    }

    private static void updateDoctor()
            throws ResourceNotFoundException, InvalidDataException {
        doctorService.updateDoctorById(
                readInt("Doctor ID: "),
                readString("New name: "),
                readString("New phone: "),
                readDouble("New consultation fee: ")
        );
    }

    private static void deleteDoctor() throws ResourceNotFoundException {
        doctorService.deleteDoctorById(readInt("Doctor ID: "));
    }

    private static void registerPatient() throws InvalidDataException {
        System.out.println("\n--- Register Patient ---");
        Patient patient = new Patient(
                IdGenerator.getNewPatientId(),
                readString("Name: "),
                readInt("Age: "),
                readString("Phone: "),
                readString("Email: "),
                readString("Blood Group: "),
                readString("Medical History: ")
        );
        patientService.createPatient(patient);
        patient.displayInfo();
    }

    private static void searchPatientById() throws ResourceNotFoundException {
        Patient patient = patientService.getPatientById(readInt("Patient ID: "));
        patient.displayInfo();
    }

    private static void searchPatientsByName() {
        listPatients(patientService.getPatientsByNameMatches(readString("Name: ")));
    }

    private static void updatePatient()
            throws ResourceNotFoundException, InvalidDataException {
        patientService.updatePatientById(
                readInt("Patient ID: "),
                readString("New name: "),
                readString("New phone: "),
                readString("New blood group: "),
                readString("New medical history: ")
        );
    }

    private static void deletePatient() throws ResourceNotFoundException {
        patientService.deletePatientById(readInt("Patient ID: "));
    }

    private static void listDoctors(List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        for (Doctor doctor : doctors) {
            doctor.displayInfo();
        }
    }

    private static void listPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        for (Patient patient : patients) {
            patient.displayInfo();
        }
    }

    private static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    private static Specialization readSpecialization() {
        Specialization[] specializations = Specialization.values();
        System.out.println("\nAvailable Specializations:");
        for (int i = 0; i < specializations.length; i++) {
            System.out.println((i + 1) + ". " + specializations[i]);
        }

        while (true) {
            int choice = readInt("Select specialization: ");
            if (choice >= 1 && choice <= specializations.length) {
                return specializations[choice - 1];
            }
            System.out.println("Invalid choice. Please select again.");
        }
    }

    private static List<TimeSlot> readAvailableSlots()
            throws InvalidDataException {

        int count = readInt("Number of available slots (0 if none): ");
        if (count < 0) {
            throw new InvalidDataException(
                    "Slot count cannot be negative."
            );
        }

        List<TimeSlot> slots = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            System.out.println("\nSlot " + i + ":");
            slots.add(new TimeSlot(
                    readDate("Date (yyyy-MM-dd): "),
                    readTime("Start time (HH:mm): "),
                    readTime("End time (HH:mm): ")
            ));
        }
        return slots;
    }

    private static LocalDate readDate(String message) {
        while (true) {
            try {
                return LocalDate.parse(readString(message));
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date (yyyy-MM-dd).");
            }
        }
    }

    private static LocalTime readTime(String message) {
        while (true) {
            try {
                return LocalTime.parse(readString(message));
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid time (HH:mm).");
            }
        }
    }
}
