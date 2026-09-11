package DesignPattern.meditrack.entity;

import DesignPattern.meditrack.exception.InvalidDataException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class TimeSlot {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(LocalDate date, LocalTime startTime, LocalTime endTime) throws InvalidDataException {
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
        validateRange();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws InvalidDataException {
        if (date == null) {
            throw new InvalidDataException("Slot date cannot be null.");
        }

        this.date = date;
        validateRangeIfComplete();
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime)
            throws InvalidDataException {

        if (startTime == null) {
            throw new InvalidDataException(
                    "Slot start time cannot be null."
            );
        }

        validateRangeIfComplete();
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) throws InvalidDataException {
        if (endTime == null) {
            throw new InvalidDataException("Slot end time cannot be null.");
        }

        validateRangeIfComplete();
        this.endTime = endTime;
    }

    public boolean overlaps(TimeSlot other) {
        if (other == null || !date.equals(other.date)) {
            return false;
        }

        return startTime.isBefore(other.endTime) && other.startTime.isBefore(endTime);
    }

    @Override
    public String toString() {
        return date + " " + startTime + " - " + endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TimeSlot)) {
            return false;
        }

        TimeSlot other = (TimeSlot) obj;
        return Objects.equals(date, other.date)
                && Objects.equals(startTime, other.startTime)
                && Objects.equals(endTime, other.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, endTime);
    }

    private void validateRangeIfComplete() throws InvalidDataException {
        if (startTime != null && endTime != null) {
            validateRange();
        }
    }

    private void validateRange() throws InvalidDataException {
        if (startTime != null && endTime != null && !startTime.isBefore(endTime)) {
            throw new InvalidDataException("Slot start time must be before end time.");
        }
    }
}
