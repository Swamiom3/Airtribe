package com.airtribe.learntrack.entity;

public class Student extends Person{
    private String batch;
    private Boolean isActive;

    public Student() {
        super();
        this.isActive = true;
    }

    public Student(int id, String firstName, String lastName, String email,
                   String batch, Boolean isActive) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.isActive = isActive;
    }

    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    @Override
    public String getDisplayName() {
        return "Student: " + getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", batch='" + batch + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
