package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private List<Enrollment> enrollments;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService,
                             CourseService courseService) {

        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollments = new ArrayList<>();
    }

    public void enrollStudent(Enrollment enrollment)
            throws InvalidInputException, EntityNotFoundException {

        if (enrollment == null) {
            throw new InvalidInputException("Enrollment cannot be null.");
        }

        studentService.findStudentById(enrollment.getStudentId());
        courseService.findCourseById(enrollment.getCourseId());
        enrollments.add(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    public Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }

        throw new EntityNotFoundException(
                "Enrollment with ID " + id + " not found."
        );
    }

    public void viewEnrollmentsByStudent(int studentId) {
        boolean found = false;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                System.out.println(enrollment);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No enrollments found.");
        }
    }

    public void completeEnrollment(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus("COMPLETED");
    }

    public void cancelEnrollment(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus("CANCELLED");
    }

    public void removeEnrollment(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollments.remove(enrollment);
    }

    public void displayEnrollments() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments available.");
            return;
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    public int getEnrollmentCount() {
        return enrollments.size();
    }
}