package DesignPattern.meditrack.util;

import DesignPattern.meditrack.config.DataLoader;

public class IdGenerator {

    public static Integer getNewPatientId() {
        return DataLoader.PATIENTS.size() + 1;
    }

    public static Integer getNewDoctorId() {
        return DataLoader.DOCTORS.size() + 1;
    }
}
