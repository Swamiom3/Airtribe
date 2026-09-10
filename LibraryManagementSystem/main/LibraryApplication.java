package DesignPattern.LibraryManagementSystem.main;

import DesignPattern.LibraryManagementSystem.Model.Book;
import DesignPattern.LibraryManagementSystem.Model.Patron;
import DesignPattern.LibraryManagementSystem.service.Library;

import java.util.List;

public class LibraryApplication {

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

        System.out.println("=== Books Added ===");
        printBooks(library.getAllBooks());


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

        System.out.println("\n=== Patrons Added ===");
        System.out.println(library.getAllPatrons());


        // ==================== SEARCH BY TITLE ====================

        System.out.println("\n=== Search By Title ===");

        List<Book> titleResults =
                library.searchBooks("title", "clean");

        printBooks(titleResults);


        // ==================== SEARCH BY AUTHOR ====================

        System.out.println("\n=== Search By Author ===");

        List<Book> authorResults =
                library.searchBooks("author", "bloch");

        printBooks(authorResults);


        // ==================== SEARCH BY ISBN ====================

        System.out.println("\n=== Search By ISBN ===");

        List<Book> isbnResults =
                library.searchBooks(
                        "isbn",
                        "9780132350884"
                );

        printBooks(isbnResults);


        // ==================== UPDATE BOOK ====================

        System.out.println("\n=== Update Book ===");

        library.updateBook(
                "9780132350884",
                "Clean Code - Updated",
                "Robert C. Martin",
                2008
        );

        printBooks(
                library.searchBooks(
                        "isbn",
                        "9780132350884"
                )
        );


        // ==================== CHECKOUT ====================

        System.out.println("\n=== Checkout Book ===");

        library.checkoutBook(
                "9780132350884",
                "P101"
        );

        System.out.println(
                "Book available: " +
                        book1.isAvailable()
        );


        // ==================== BORROWING HISTORY ====================

        System.out.println("\n=== Borrowing History ===");

        patron1.getBorrowingHistory()
                .forEach(System.out::println);


        // ==================== AVAILABLE BOOKS ====================

        System.out.println("\n=== Available Books ===");

        printBooks(library.getAvailableBooks());


        // ==================== RETURN BOOK ====================

        System.out.println("\n=== Return Book ===");

        library.returnBook(
                "9780132350884",
                "P101"
        );

        System.out.println(
                "Book available: " +
                        book1.isAvailable()
        );


        // ==================== BORROWING HISTORY ====================

        System.out.println("\n=== Borrowing History After Return ===");

        patron1.getBorrowingHistory()
                .forEach(System.out::println);


        // ==================== REMOVE BOOK ====================

        System.out.println("\n=== Remove Book ===");

        library.removeBook(
                "9780201633610"
        );

        System.out.println(
                "Total books: " +
                        library.getAllBooks().size()
        );

        System.out.println("\n=== Application Completed ===");
    }

    private static void printBooks(List<Book> books) {

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        books.forEach(System.out::println);
    }
}
