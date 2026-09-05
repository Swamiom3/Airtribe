package DesignPattern.LibraryManagementSystem.Stratergy;

import DesignPattern.LibraryManagementSystem.Model.Book;

import java.util.List;

public interface SearchStrategy {

    List<Book> search(List<Book> books, String keyword);
}