package DesignPattern.meditrack.entity;

import DesignPattern.meditrack.constants.Specialization;
import DesignPattern.meditrack.exception.InvalidDataException;
import DesignPattern.meditrack.interfaces.Searchable;
import DesignPattern.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class Doctor
        extends Person
        implements Searchable<Doctor> {

    private Specialization specialization;
    private double consultationFee;
    private List<TimeSlot> availableSlots = new ArrayList<>();

    public Doctor(
            int id,
            String name,
            int age,
            String phone,
            String email,
            Specialization specialization,
            double consultationFee,
            List<TimeSlot> availableSlots
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
        setAvailableSlots(availableSlots);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public List<TimeSlot> getAvailableSlots() {
        return new ArrayList<>(availableSlots);
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

    public void setAvailableSlots(List<TimeSlot> availableSlots) throws InvalidDataException {

        if (availableSlots == null) {
            throw new InvalidDataException("Available slots cannot be null.");
        }

        List<TimeSlot> copy = new ArrayList<>();
        for (TimeSlot slot : availableSlots) {
            addSlotTo(copy, slot);
        }

        this.availableSlots = copy;
    }

    public void addAvailableSlot(TimeSlot slot) throws InvalidDataException {
        addSlotTo(availableSlots, slot);
    }

    public boolean removeAvailableSlot(TimeSlot slot) {
        return availableSlots.remove(slot);
    }

    @Override
    public boolean matches(String keyword) {

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            return false;
        }

        String search =
                keyword.trim().toLowerCase();

        return String.valueOf(getId())
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

        System.out.println("Available Slots :");
        if (availableSlots.isEmpty()) {
            System.out.println("  None");
        } else {
            for (TimeSlot slot : availableSlots) {
                System.out.println("  " + slot);
            }
        }
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
                ", availableSlots=" +
                availableSlots +
                '}';
    }

    private void addSlotTo(List<TimeSlot> slots, TimeSlot slot) throws InvalidDataException {
        if (slot == null) {
            throw new InvalidDataException("Time slot cannot be null.");
        }

        for (TimeSlot existing : slots) {
            if (existing.overlaps(slot)) {
                throw new InvalidDataException("Time slot overlaps an existing slot: " + existing);
            }
        }

        slots.add(slot);
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

        return getId() == other.getId();
    }

    @Override
    public int hashCode() {

        return Integer.hashCode(getId());
    }
}
