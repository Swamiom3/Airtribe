package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private List<Course> courses;

    public CourseService() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) throws InvalidInputException {

        if (course == null) {
            throw new InvalidInputException("Course cannot be null.");
        }

        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            throw new InvalidInputException("Course name cannot be empty.");
        }

        if (course.getDurationInWeeks() <= 0) {
            throw new InvalidInputException("Duration must be greater than 0.");
        }

        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }

        throw new EntityNotFoundException(
                "Course with ID " + id + " not found."
        );
    }

    public void updateCourse(int id,
                             String courseName,
                             String description,
                             int durationInWeeks)
            throws EntityNotFoundException {

        Course course = findCourseById(id);

        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);
    }

    public void activateCourse(int id) throws EntityNotFoundException {

        Course course = findCourseById(id);
        course.setActive(true);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {

        Course course = findCourseById(id);
        course.setActive(false);
    }

    public void removeCourse(int id) throws EntityNotFoundException {

        Course course = findCourseById(id);
        courses.remove(course);
    }

    public void displayCourses() {

        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public int getCourseCount() {
        return courses.size();
    }
}