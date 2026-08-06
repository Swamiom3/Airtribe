package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

        boolean exit = false;

        while (!exit) {

            System.out.println("\n========== LearnTrack ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. View Courses");
            System.out.println("5. Enroll Student");
            System.out.println("6. View Enrollments");
            System.out.println("7. Complete Enrollment");
            System.out.println("8. Cancel Enrollment");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            try {

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("First Name: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Last Name: ");
                        String lastName = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Batch: ");
                        String batch = scanner.nextLine();

                        Student student = new Student(
                                IdGenerator.getNextStudentId(),
                                firstName,
                                lastName,
                                email,
                                batch,
                                true
                        );

                        studentService.addStudent(student);

                        System.out.println("Student added successfully.");

                        break;

                    case 2:

                        studentService.displayStudents();

                        break;

                    case 3:

                        System.out.print("Course Name: ");
                        String courseName = scanner.nextLine();

                        System.out.print("Description: ");
                        String description = scanner.nextLine();

                        System.out.print("Duration (Weeks): ");
                        int duration =
                                Integer.parseInt(scanner.nextLine());

                        Course course = new Course(
                                IdGenerator.getNextCourseId(),
                                courseName,
                                description,
                                duration,
                                true
                        );

                        courseService.addCourse(course);

                        System.out.println("Course added successfully.");

                        break;

                    case 4:

                        courseService.displayCourses();

                        break;

                    case 5:

                        System.out.print("Student ID: ");
                        int studentId =
                                Integer.parseInt(scanner.nextLine());

                        System.out.print("Course ID: ");
                        int courseId =
                                Integer.parseInt(scanner.nextLine());

                        Enrollment enrollment = new Enrollment(
                                IdGenerator.getNextEnrollmentId(),
                                studentId,
                                courseId
                        );

                        enrollmentService.enrollStudent(enrollment);

                        System.out.println("Enrollment successful.");

                        break;

                    case 6:

                        enrollmentService.displayEnrollments();

                        break;

                    case 7:

                        System.out.print("Enrollment ID: ");
                        int completeId =
                                Integer.parseInt(scanner.nextLine());

                        enrollmentService.completeEnrollment(completeId);

                        System.out.println("Enrollment completed.");

                        break;

                    case 8:

                        System.out.print("Enrollment ID: ");
                        int cancelId =
                                Integer.parseInt(scanner.nextLine());

                        enrollmentService.cancelEnrollment(cancelId);

                        System.out.println("Enrollment cancelled.");

                        break;

                    case 9:

                        exit = true;
                        System.out.println("Thank you for using LearnTrack.");
                        break;

                    default:

                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");

            } catch (EntityNotFoundException | InvalidInputException e) {

                System.out.println(e.getMessage());

            } catch (Exception e) {

                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}