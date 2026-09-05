package DesignPattern.LibraryManagementSystem.Model;

import DesignPattern.LibraryManagementSystem.service.Library;

import java.util.List;

public class LibraryDemo {

    public static void main(String[] args) {

        Library library = new Library();

        // ==================== ADD BOOKS ====================

        Book book1 = new Book(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008
        );

        Book book2 = new Book(
                "Effective Java",
                "Joshua Bloch",
                "9780134685991",
                2018
        );

        Book book3 = new Book(
                "Design Patterns",
                "Erich Gamma",
                "9780201633610",
                1994
        );

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println("Books added successfully.");

        // ==================== ADD PATRONS ====================

        Patron patron1 = new Patron(
                "P101",
                "Onkar",
                "onkar@gmail.com"
        );

        Patron patron2 = new Patron(
                "P102",
                "Rahul",
                "rahul@gmail.com"
        );

        library.addPatron(patron1);
        library.addPatron(patron2);

        System.out.println("Patrons added successfully.");

        // ==================== SEARCH BY TITLE ====================

        System.out.println("\nSearch by title:");

        List<Book> titleResults =
                library.searchBooks("title", "clean");

        for (Book book : titleResults) {
            System.out.println(book);
        }

        // ==================== SEARCH BY AUTHOR ====================

        System.out.println("\nSearch by author:");

        List<Book> authorResults =
                library.searchBooks("author", "bloch");

        for (Book book : authorResults) {
            System.out.println(book);
        }

        // ==================== SEARCH BY ISBN ====================

        System.out.println("\nSearch by ISBN:");

        List<Book> isbnResults =
                library.searchBooks(
                        "isbn",
                        "9780132350884"
                );

        for (Book book : isbnResults) {
            System.out.println(book);
        }

        // ==================== UPDATE BOOK ====================

        System.out.println("\nUpdating book...");

        library.updateBook(
                "9780132350884",
                "Clean Code - Updated",
                "Robert C. Martin",
                2008
        );

        System.out.println(
                library.searchBooks(
                        "isbn",
                        "9780132350884"
                )
        );

        // ==================== CHECKOUT ====================

        System.out.println("\nChecking out book...");

        library.checkoutBook(
                "9780132350884",
                "P101"
        );

        System.out.println(
                "Book available: " + book1.isAvailable()
        );

        // ==================== BORROWING HISTORY ====================

        System.out.println("\nBorrowing history:");

        for (BorrowRecord record :
                patron1.getBorrowingHistory()) {

            System.out.println(record);
        }

        // ==================== AVAILABLE BOOKS ====================

        System.out.println("\nAvailable books:");

        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getTitle());
        }

        // ==================== RETURN ====================

        System.out.println("\nReturning book...");

        library.returnBook(
                "9780132350884",
                "P101"
        );

        System.out.println(
                "Book available: " + book1.isAvailable()
        );

        // ==================== BORROWING HISTORY AFTER RETURN ====================

        System.out.println("\nBorrowing history after return:");

        for (BorrowRecord record :
                patron1.getBorrowingHistory()) {

            System.out.println(record);
        }

        // ==================== REMOVE BOOK ====================

        System.out.println("\nRemoving book...");

        library.removeBook("9780201633610");

        System.out.println(
                "Total books: " +
                        library.getAllBooks().size()
        );

        System.out.println("\nLibrary demo completed.");
    }
}
