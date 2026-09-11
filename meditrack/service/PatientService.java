package DesignPattern.meditrack.service;

import DesignPattern.meditrack.config.DataLoader;
import DesignPattern.meditrack.entity.Patient;
import DesignPattern.meditrack.exception.InvalidDataException;
import DesignPattern.meditrack.exception.ResourceNotFoundException;
import DesignPattern.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    //get all patients
    public List<Patient> getPatients() {
        return new ArrayList<>(DataLoader.PATIENTS);
    }

    //get patient by id
    public Patient getPatientById(int id) throws ResourceNotFoundException {
        for (Patient patient : DataLoader.PATIENTS) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        throw new ResourceNotFoundException("Patient not found with id:" + id);
    }

    //get patient by name
    public List<Patient> getPatientsByNameMatches(String name) {
        List<Patient> patients = new ArrayList<>();
        for (Patient patient : DataLoader.PATIENTS) {
            if (patient.getName().toLowerCase().contains(name.toLowerCase())) {
                patients.add(patient);
            }
        }
        return patients;
    }

    //create patient
    public void createPatient(Patient patient) {
        DataLoader.PATIENTS.add(patient);
        System.out.println("Patient " + patient.getName() + " registered successfully");
    }

    //update patient by id
    public void updatePatientById(int id, String name, String phoneNum, String bloodGroup, String medicalHistory)
            throws ResourceNotFoundException, InvalidDataException {
        Patient patientById = getPatientById(id);

        Validator.validateName(name);
        Validator.validatePhone(phoneNum);

        patientById.setName(name);
        patientById.setPhone(phoneNum);
        patientById.setBloodGroup(bloodGroup);
        patientById.setMedicalHistory(medicalHistory);
        System.out.println("Patient with id:" + id + " updated successfully");
    }

    //delete patient by id
    public void deletePatientById(int id) throws ResourceNotFoundException {
        Patient patientById = getPatientById(id);
        DataLoader.PATIENTS.remove(patientById);
        System.out.println("Patient with id:" + id + " deleted successfully");
    }
}
