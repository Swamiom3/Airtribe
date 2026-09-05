package DesignPattern.LibraryManagementSystem.factory;

import DesignPattern.LibraryManagementSystem.Stratergy.AuthorSearchStrategy;
import DesignPattern.LibraryManagementSystem.Stratergy.IsbnSearchStrategy;
import DesignPattern.LibraryManagementSystem.Stratergy.SearchStrategy;
import DesignPattern.LibraryManagementSystem.Stratergy.TitleSearchStrategy;

public class SearchStrategyFactory {

    public static SearchStrategy getStrategy(String type) {

        switch (type.toLowerCase()) {

            case "title":
                return new TitleSearchStrategy();

            case "author":
                return new AuthorSearchStrategy();

            case "isbn":
                return new IsbnSearchStrategy();

            default:
                throw new IllegalArgumentException(
                        "Invalid search type: " + type
                );
        }
    }
}
