package DesignPattern.LibraryManagementSystem.Model;

import java.time.LocalDate;

public class BorrowRecord {

    private Book book;
    private LocalDate checkoutDate;
    private LocalDate returnDate;

    public BorrowRecord(Book book) {
        this.book = book;
        this.checkoutDate = LocalDate.now();
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void markReturned() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "book=" + book.getTitle() +
                ", checkoutDate=" + checkoutDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
