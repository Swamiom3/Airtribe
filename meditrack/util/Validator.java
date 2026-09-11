package DesignPattern.meditrack.util;

import DesignPattern.meditrack.exception.InvalidDataException;

public final class Validator {

    // Prevent object creation
    private Validator() {
    }

    public static void validateName(String name)
            throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException(
                    "Name cannot be empty."
            );
        }

        if (!name.matches("[a-zA-Z ]+")) {
            throw new InvalidDataException(
                    "Name can contain only letters and spaces."
            );
        }
    }

    public static void validateAge(int age)
            throws InvalidDataException {

        if (age <= 0 || age > 120) {
            throw new InvalidDataException(
                    "Age must be between 1 and 120."
            );
        }
    }

    public static void validatePhone(String phone)
            throws InvalidDataException {

        if (phone == null || !phone.matches("\\d{10}")) {
            throw new InvalidDataException(
                    "Phone number must contain exactly 10 digits."
            );
        }
    }

    public static void validateEmail(String email)
            throws InvalidDataException {

        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException(
                    "Email cannot be empty."
            );
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new InvalidDataException(
                    "Invalid email format."
            );
        }
    }

    public static void validatePositiveAmount(double amount)
            throws InvalidDataException {

        if (amount < 0) {
            throw new InvalidDataException(
                    "Amount cannot be negative."
            );
        }
    }
}
