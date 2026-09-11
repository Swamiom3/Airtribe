package DesignPattern.meditrack.interfaces;

public interface Searchable<T> {

    boolean matches(String keyword);

    default void printSearchInfo() {
        System.out.println("Searching MediTrack records...");
    }
}