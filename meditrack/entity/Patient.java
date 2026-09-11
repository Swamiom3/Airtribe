package DesignPattern.meditrack.entity;

import DesignPattern.meditrack.exception.InvalidDataException;
import DesignPattern.meditrack.interfaces.Searchable;
import DesignPattern.meditrack.util.Validator;

public class Patient
        extends Person
        implements Searchable<Patient>, Cloneable {

    private String bloodGroup;
    private String medicalHistory;

    public Patient(
            String id,
            String name,
            int age,
            String phone,
            String email,
            String bloodGroup,
            String medicalHistory
    ) throws InvalidDataException {

        super(
                id,
                name,
                age,
                phone,
                email
        );

        setBloodGroup(bloodGroup);
        setMedicalHistory(medicalHistory);
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setBloodGroup(
            String bloodGroup
    ) throws InvalidDataException {

        if (bloodGroup == null ||
                bloodGroup.trim().isEmpty()) {

            throw new InvalidDataException(
                    "Blood group cannot be empty."
            );
        }

        this.bloodGroup =
                bloodGroup.trim().toUpperCase();
    }

    public void setMedicalHistory(
            String medicalHistory
    ) throws InvalidDataException {

        if (medicalHistory == null ||
                medicalHistory.trim().isEmpty()) {

            throw new InvalidDataException(
                    "Medical history cannot be empty."
            );
        }

        this.medicalHistory =
                medicalHistory.trim();
    }

    @Override
    public boolean matches(String keyword) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            return false;
        }

        String search =
                keyword.trim().toLowerCase();

        return getId()
                .toLowerCase()
                .contains(search)

                || getName()
                .toLowerCase()
                .contains(search)

                || bloodGroup
                .toLowerCase()
                .contains(search)

                || medicalHistory
                .toLowerCase()
                .contains(search);
    }


    public boolean searchPatient(String id) {

        return getId()
                .equalsIgnoreCase(id);
    }

    public boolean searchPatientByName(
            String name) {

        return getName()
                .equalsIgnoreCase(name);
    }

    public boolean searchPatientByAge(
            int age) {

        return getAge() == age;
    }

    @Override
    public void displayInfo() {

        System.out.println(
                "\n===== PATIENT ====="
        );

        displayBasicInfo();

        System.out.println(
                "Blood Group : "
                        + bloodGroup
        );

        System.out.println(
                "Medical History : "
                        + medicalHistory
        );
    }

    @Override
    public String getPersonType() {

        return "PATIENT";
    }

    // =========================
    // CLONING
    // =========================

    @Override
    public Patient clone() {

        try {

            return (Patient) super.clone();

        } catch (CloneNotSupportedException e) {

            throw new AssertionError(
                    "Patient cloning failed.",
                    e
            );
        }
    }

    // =========================
    // toString
    // =========================

    @Override
    public String toString() {

        return "Patient{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", medicalHistory='" +
                medicalHistory + '\'' +
                '}';
    }

    // =========================
    // equals & hashCode
    // =========================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Patient)) {
            return false;
        }

        Patient other = (Patient) obj;

        return getId().equals(other.getId());
    }

    @Override
    public int hashCode() {

        return getId().hashCode();
    }
}
