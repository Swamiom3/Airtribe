package DesignPattern.meditrack.config;

import DesignPattern.meditrack.constants.Specialization;
import DesignPattern.meditrack.entity.Doctor;
import DesignPattern.meditrack.entity.Patient;
import DesignPattern.meditrack.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public static final List<Doctor> DOCTORS = new ArrayList<>();
    public static final List<Patient> PATIENTS = new ArrayList<>();


    static {
        //Load Doctors
        System.out.println("Loading Data of Doctors");
        try {
            Doctor doc1 = new Doctor(1, "Sumit", 35, "1111111111", "doc1@gmail.com", Specialization.DENTIST, 500);
            Doctor doc2 = new Doctor(2, "Nilesh", 36, "1111111112", "doc2@gmail.com", Specialization.DERMATOLOGIST, 700);
            Doctor doc3 = new Doctor(3, "Omkar", 25, "1111111113", "doc3@gmail.com", Specialization.CARDIOLOGIST, 1000);
            DOCTORS.add(doc1);
            DOCTORS.add(doc2);
            DOCTORS.add(doc3);

        } catch (InvalidDataException e) {
            System.out.println("Error while loading doctors: " + e.getMessage());
            throw new RuntimeException(e);
        }

        //Load Patients
        System.out.println("Loading Data of Patients");
        try {
            Patient p1 = new Patient(1, "Honey", 21, "8888888888", "p1@gmail.com", "A+ve", "None");
            Patient p2 = new Patient(2, "Raftar", 22, "8888888889", "p2@gmail.com", "O+ve", "None");
            Patient p3 = new Patient(3, "Badshah", 23, "8888888890", "p3@gmail.com", "B-ve", "None");
            PATIENTS.add(p1);
            PATIENTS.add(p2);
            PATIENTS.add(p3);
        } catch (InvalidDataException e) {
            System.out.println("Error while loading doctors: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
