package DesignPattern.meditrack.entity;

import DesignPattern.meditrack.constants.Specialization;
import DesignPattern.meditrack.exception.InvalidDataException;
import DesignPattern.meditrack.interfaces.Searchable;
import DesignPattern.meditrack.util.Validator;

public class Doctor
        extends Person
        implements Searchable<Doctor> {

    private Specialization specialization;
    private double consultationFee;

    public Doctor(
            String id,
            String name,
            int age,
            String phone,
            String email,
            Specialization specialization,
            double consultationFee
    ) throws InvalidDataException {

        /*
         * Calls Person constructor.
         */
        super(
                id,
                name,
                age,
                phone,
                email
        );

        setSpecialization(specialization);
        setConsultationFee(consultationFee);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setSpecialization(
            Specialization specialization
    ) throws InvalidDataException {

        if (specialization == null) {

            throw new InvalidDataException(
                    "Specialization cannot be null."
            );
        }

        this.specialization = specialization;
    }

    public void setConsultationFee(
            double consultationFee
    ) throws InvalidDataException {

        Validator.validatePositiveAmount(
                consultationFee
        );

        this.consultationFee = consultationFee;
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

                || specialization
                .name()
                .toLowerCase()
                .contains(search);
    }

    @Override
    public void displayInfo() {

        System.out.println(
                "\n===== DOCTOR ====="
        );

        displayBasicInfo();

        System.out.println(
                "Specialization : "
                        + specialization
        );

        System.out.println(
                "Consultation Fee : ₹"
                        + consultationFee
        );
    }

    @Override
    public String getPersonType() {

        return "DOCTOR";
    }

    @Override
    public String toString() {

        return "Doctor{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", specialization=" + specialization +
                ", consultationFee=" +
                consultationFee +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Doctor)) {
            return false;
        }

        Doctor other = (Doctor) obj;

        return getId().equals(other.getId());
    }

    @Override
    public int hashCode() {

        return getId().hashCode();
    }
}
