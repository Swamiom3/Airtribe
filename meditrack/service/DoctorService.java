package DesignPattern.meditrack.service;

import DesignPattern.meditrack.config.DataLoader;
import DesignPattern.meditrack.constants.Specialization;
import DesignPattern.meditrack.entity.Doctor;
import DesignPattern.meditrack.exception.InvalidDataException;
import DesignPattern.meditrack.exception.ResourceNotFoundException;
import DesignPattern.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DoctorService {

    //get all doctors
    public List<Doctor> getDoctors() {
        return new ArrayList<>(DataLoader.DOCTORS);
    }

    //get doctor by id
    public Doctor getDoctorById(int id) throws ResourceNotFoundException {
        for (Doctor doctor : DataLoader.DOCTORS) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        throw new ResourceNotFoundException("Doctor not found with id:" + id);
    }


    //get all doctors by specializations
    public List<Doctor> getDoctorsBySpecializations(Set<Specialization> specializations) {
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor : DataLoader.DOCTORS) {
            if (specializations.contains(doctor.getSpecialization())) {
                doctors.add(doctor);
            }
        }
        return doctors;
    }


    //get all doctors by name
    public List<Doctor> getDoctorsByNameMatches(String name) {
        List<Doctor> doctors = new ArrayList<>();
        for (Doctor doctor : DataLoader.DOCTORS) {
            if (doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    //create doctor
    public void createDoctor(Doctor doctor) {
        DataLoader.DOCTORS.add(doctor);
        System.out.println("Doctor "+ doctor.getName() +" registered successfully");
    }

    //update doctor by id
    public void updateDoctorById(int id, String name, String phoneNum, double charges) throws ResourceNotFoundException, InvalidDataException {
        Doctor doctorById = getDoctorById(id);

        Validator.validateName(name);
        Validator.validatePhone(phoneNum);
        Validator.validatePositiveAmount(charges);

        doctorById.setName(name);
        doctorById.setPhone(phoneNum);
        doctorById.setConsultationFee(charges);
        System.out.println("Doctor with id:"+ id + " updated successfully");
    }

    //delete doctor by id
    public void deleteDoctorById(int id) throws ResourceNotFoundException {
        Doctor doctorById = getDoctorById(id);
        DataLoader.DOCTORS.remove(doctorById);
        System.out.println("Doctor with id:"+ id + " deleted successfully");
    }
}
