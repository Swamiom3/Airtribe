package DesignPattern.LibraryManagementSystem.Stratergy;

import DesignPattern.LibraryManagementSystem.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class IsbnSearchStrategy implements SearchStrategy {

    @Override
    public List<Book> search(List<Book> books, String keyword) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getIsbn().equals(keyword)) {
                result.add(book);
                break;
            }
        }

        return result;
    }
}