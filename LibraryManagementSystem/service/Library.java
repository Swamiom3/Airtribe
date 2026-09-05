package DesignPattern.LibraryManagementSystem.service;

import DesignPattern.LibraryManagementSystem.Model.Book;
import DesignPattern.LibraryManagementSystem.Model.BorrowRecord;
import DesignPattern.LibraryManagementSystem.Model.Patron;
import DesignPattern.LibraryManagementSystem.Stratergy.SearchStrategy;
import DesignPattern.LibraryManagementSystem.factory.SearchStrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private Map<String, Book> books;
    private Map<String, Patron> patrons;

    public Library() {
        books = new HashMap<>();
        patrons = new HashMap<>();
    }

    // ==================== BOOK MANAGEMENT ====================

    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException(
                    "Book with ISBN already exists: " + book.getIsbn()
            );
        }

        books.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn) {
        if (!books.containsKey(isbn)) {
            throw new IllegalArgumentException(
                    "Book not found: " + isbn
            );
        }

        books.remove(isbn);
    }

    public void updateBook(
            String isbn,
            String title,
            String author,
            int publicationYear) {

        Book book = books.get(isbn);

        if (book == null) {
            throw new IllegalArgumentException(
                    "Book not found: " + isbn
            );
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
    }

    // ==================== BOOK SEARCH ====================

    public List<Book> searchBooks(String type, String keyword) {

        SearchStrategy strategy =
                SearchStrategyFactory.getStrategy(type);

        return strategy.search(
                new ArrayList<>(books.values()),
                keyword
        );
    }

    // ==================== PATRON MANAGEMENT ====================

    public void addPatron(Patron patron) {

        if (patrons.containsKey(patron.getPatronId())) {
            throw new IllegalArgumentException(
                    "Patron already exists: " + patron.getPatronId()
            );
        }

        patrons.put(patron.getPatronId(), patron);
    }

    public void updatePatron(
            String patronId,
            String name,
            String email) {

        Patron patron = patrons.get(patronId);

        if (patron == null) {
            throw new IllegalArgumentException(
                    "Patron not found: " + patronId
            );
        }

        patron.setName(name);
        patron.setEmail(email);
    }

    // ==================== LENDING ====================

    public void checkoutBook(
            String isbn,
            String patronId) {

        Book book = books.get(isbn);
        Patron patron = patrons.get(patronId);

        if (book == null) {
            throw new IllegalArgumentException(
                    "Book not found: " + isbn
            );
        }

        if (patron == null) {
            throw new IllegalArgumentException(
                    "Patron not found: " + patronId
            );
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException(
                    "Book is already borrowed: " + isbn
            );
        }

        book.setAvailable(false);

        BorrowRecord record = new BorrowRecord(book);

        patron.addBorrowRecord(record);
    }

    public void returnBook(
            String isbn,
            String patronId) {

        Book book = books.get(isbn);
        Patron patron = patrons.get(patronId);

        if (book == null) {
            throw new IllegalArgumentException(
                    "Book not found: " + isbn
            );
        }

        if (patron == null) {
            throw new IllegalArgumentException(
                    "Patron not found: " + patronId
            );
        }

        if (book.isAvailable()) {
            throw new IllegalStateException(
                    "Book is not currently borrowed: " + isbn
            );
        }

        for (BorrowRecord record : patron.getBorrowingHistory()) {

            if (record.getBook().getIsbn().equals(isbn)
                    && record.getReturnDate() == null) {

                record.markReturned();
                book.setAvailable(true);

                return;
            }
        }

        throw new IllegalStateException(
                "This patron has not borrowed this book."
        );
    }

    // ==================== INVENTORY ====================

    public List<Book> getAvailableBooks() {

        List<Book> availableBooks = new ArrayList<>();

        for (Book book : books.values()) {

            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }

        return availableBooks;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patrons.values());
    }
}
