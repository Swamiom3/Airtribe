package com.airtribe.learntrack.entity;

public class Person {
    //Fields: id, firstName, lastName, email
    private int id;
    private String FirstName;
    private String LastName;
    private String Email;

    Person() {}

    Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getDisplayName() {
        return FirstName + " " + LastName;
    }
}
