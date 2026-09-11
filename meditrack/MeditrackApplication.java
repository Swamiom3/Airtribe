package DesignPattern.meditrack;

import DesignPattern.meditrack.entity.Doctor;
import DesignPattern.meditrack.entity.Patient;
import DesignPattern.meditrack.entity.Person;
import DesignPattern.meditrack.constants.Specialization;
import DesignPattern.meditrack.exception.InvalidDataException;

import java.util.Scanner;

public class MeditrackApplication {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("        Welcome to MediTrack");
        System.out.println("====================================");

        try {

            System.out.println("\n--- Enter Patient Details ---");

            String patientId = readString("Patient ID: ");

            String patientName = readString("Patient Name: ");

            int patientAge = readInt("Patient Age: ");

            String patientPhone = readString("Patient Phone: ");

            String patientEmail = readString("Patient Email: ");

            String bloodGroup = readString("Blood Group: ");

            String medicalHistory =
                    readString("Medical History: ");

            Patient patient = new Patient(
                    patientId,
                    patientName,
                    patientAge,
                    patientPhone,
                    patientEmail,
                    bloodGroup,
                    medicalHistory
            );

            System.out.println("\n--- Patient Details ---");
            patient.displayInfo();

            System.out.println("\n--- Enter Doctor Details ---");

            String doctorId = readString("Doctor ID: ");

            String doctorName = readString("Doctor Name: ");

            int doctorAge = readInt("Doctor Age: ");

            String doctorPhone = readString("Doctor Phone: ");

            String doctorEmail = readString("Doctor Email: ");

            Specialization specialization =
                    readSpecialization();

            double consultationFee =
                    readDouble("Consultation Fee: ");

            Doctor doctor = new Doctor(
                    doctorId,
                    doctorName,
                    doctorAge,
                    doctorPhone,
                    doctorEmail,
                    specialization,
                    consultationFee
            );

            System.out.println("\n--- Doctor Details ---");
            doctor.displayInfo();

            System.out.println(
                    "\n--- Encapsulation Demo ---"
            );

            System.out.println(
                    "Current patient name: "
                            + patient.getName()
            );

            String updatedName =
                    readString(
                            "Enter new patient name: "
                    );

            patient.setName(updatedName);

            System.out.println(
                    "Updated patient name: "
                            + patient.getName()
            );


            System.out.println(
                    "\n--- Inheritance Demo ---"
            );

            System.out.println(
                    "Doctor is a Person: "
                            + (doctor instanceof Person)
            );

            System.out.println(
                    "Patient is a Person: "
                            + (patient instanceof Person)
            );

            System.out.println(
                    "\n--- Polymorphism Demo ---"
            );

            Person person;

            // Doctor object assigned to Person reference
            person = doctor;

            System.out.println(
                    "\nPerson reference pointing to Doctor:"
            );

            person.displayInfo();

            System.out.println(
                    "Person Type: "
                            + person.getPersonType()
            );

            // Patient object assigned to Person reference
            person = patient;

            System.out.println(
                    "\nPerson reference pointing to Patient:"
            );

            person.displayInfo();

            System.out.println(
                    "Person Type: "
                            + person.getPersonType()
            );


            System.out.println(
                    "\n--- Search Demo ---"
            );

            System.out.println(
                    "\nHow do you want to search the patient?"
            );

            System.out.println("1. Search by ID");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Age");

            int searchChoice =
                    readInt("Enter choice: ");

            switch (searchChoice) {

                case 1:

                    String searchId =
                            readString(
                                    "Enter Patient ID: "
                            );

                    System.out.println(
                            "Patient found: "
                                    + patient.searchPatient(
                                    searchId
                            )
                    );

                    break;

                case 2:

                    String searchName =
                            readString(
                                    "Enter Patient Name: "
                            );

                    System.out.println(
                            "Patient found: "
                                    + patient.searchPatientByName(
                                    searchName
                            )
                    );

                    break;

                case 3:

                    int searchAge =
                            readInt(
                                    "Enter Patient Age: "
                            );

                    System.out.println(
                            "Patient found: "
                                    + patient.searchPatientByAge(
                                    searchAge
                            )
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid search choice."
                    );
            }

            System.out.println(
                    "\n--- Doctor Search ---"
            );

            String doctorSearch =
                    readString(
                            "Enter keyword to search doctor: "
                    );

            System.out.println(
                    "Doctor found: "
                            + doctor.matches(
                            doctorSearch
                    )
            );

            System.out.println(
                    "\n--- Cloning Demo ---"
            );

            Patient clonedPatient =
                    patient.clone();

            System.out.println(
                    "Original object == cloned object: "
                            + (patient == clonedPatient)
            );

            System.out.println(
                    "Original object equals clone: "
                            + patient.equals(
                            clonedPatient
                    )
            );

            System.out.println(
                    "Original Patient ID: "
                            + patient.getId()
            );

            System.out.println(
                    "Cloned Patient ID: "
                            + clonedPatient.getId()
            );
            System.out.println(
                    "\n--- equals() and hashCode() Demo ---"
            );

            System.out.println(
                    "\nCreate another patient for comparison."
            );

            String anotherPatientId =
                    readString(
                            "Another Patient ID: "
                    );

            String anotherPatientName =
                    readString(
                            "Another Patient Name: "
                    );

            int anotherPatientAge =
                    readInt(
                            "Another Patient Age: "
                    );

            String anotherPatientPhone =
                    readString(
                            "Another Patient Phone: "
                    );

            String anotherPatientEmail =
                    readString(
                            "Another Patient Email: "
                    );

            String anotherBloodGroup =
                    readString(
                            "Another Patient Blood Group: "
                    );

            String anotherMedicalHistory =
                    readString(
                            "Another Patient Medical History: "
                    );

            Patient anotherPatient =
                    new Patient(
                            anotherPatientId,
                            anotherPatientName,
                            anotherPatientAge,
                            anotherPatientPhone,
                            anotherPatientEmail,
                            anotherBloodGroup,
                            anotherMedicalHistory
                    );

            System.out.println(
                    "\nPatient 1 equals Patient 2: "
                            + patient.equals(
                            anotherPatient
                    )
            );

            System.out.println(
                    "Patient 1 hashCode: "
                            + patient.hashCode()
            );

            System.out.println(
                    "Patient 2 hashCode: "
                            + anotherPatient.hashCode()
            );

            System.out.println(
                    "\n--- Person Type Demo ---"
            );

            System.out.println(
                    "Doctor Type: "
                            + doctor.getPersonType()
            );

            System.out.println(
                    "Patient Type: "
                            + patient.getPersonType()
            );


            System.out.println(
                    "\n--- toString() Demo ---"
            );

            System.out.println(
                    "Patient:"
            );

            System.out.println(patient);

            System.out.println(
                    "\nDoctor:"
            );

            System.out.println(doctor);
            System.out.println(
                    "\n===================================="
            );

            System.out.println(
                    "    MediTrack Demo Completed"
            );

            System.out.println(
                    "===================================="
            );

        } catch (InvalidDataException e) {

            System.out.println(
                    "\nInvalid data: "
                            + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "\nUnexpected error: "
                            + e.getMessage()
            );
        }

        scanner.close();
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }

    private static Specialization readSpecialization() {

        Specialization[] specializations =
                Specialization.values();

        System.out.println(
                "\nAvailable Specializations:"
        );

        for (int i = 0;
             i < specializations.length;
             i++) {

            System.out.println(
                    (i + 1)
                            + ". "
                            + specializations[i]
            );
        }

        while (true) {

            int choice =
                    readInt(
                            "Select specialization: "
                    );

            if (choice >= 1 &&
                    choice <= specializations.length) {

                return specializations[choice - 1];
            }

            System.out.println(
                    "Invalid choice. Please select again."
            );
        }
    }
}