package DesignPattern.LibraryManagementSystem.Model;

import java.util.ArrayList;
import java.util.List;

public class Patron {

    private String patronId;
    private String name;
    private String email;

    private List<BorrowRecord> borrowingHistory;

    public Patron(String patronId, String name, String email) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.borrowingHistory = new ArrayList<>();
    }

    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BorrowRecord> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void addBorrowRecord(BorrowRecord record) {
        borrowingHistory.add(record);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patronId='" + patronId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
