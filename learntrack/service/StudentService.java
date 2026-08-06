package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students;

    public StudentService() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) throws InvalidInputException {

        if (student == null) {
            throw new InvalidInputException("Student cannot be null.");
        }

        if (student.getFirstName() == null ||
                student.getFirstName().trim().isEmpty()) {

            throw new InvalidInputException("First name cannot be empty.");
        }

        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student findStudentById(int id)
            throws EntityNotFoundException {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        throw new EntityNotFoundException(
                "Student with ID " + id + " not found."
        );
    }

    public void updateStudent(int id,
                              String firstName,
                              String lastName,
                              String email,
                              String batch)
            throws EntityNotFoundException {

        Student student = findStudentById(id);

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
    }

    public void deactivateStudent(int id)
            throws EntityNotFoundException {

        Student student = findStudentById(id);
        student.setIsActive(false);
    }

    public void removeStudent(int id)
            throws EntityNotFoundException {
        Student student = findStudentById(id);
        students.remove(student);
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public int getStudentCount() {
        return students.size();
    }
}