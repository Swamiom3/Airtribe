# Project Description.

We have to create a project which is learnertrack.
where we have students, couress and enrollment from student for specific course.
where we have person object extedns by student adn trainer(optinal not included),
enrollemnt is a many to many relationship where student have multiple courses and course have multiple studens.

We have main class where we show all menu for add, deactivate , search for student and course as well.
we have service classes to handle all the validation and service related logic.

we have  **IdGenerator** class to auto generates the id's for student and course.


Q2 : How to run the project.
Clone the repo in you local ide and run the **Main** class.
You can select Menu as you want.

# Class Diagram



```text
                          +----------------------+
                          |       Person         |
                          +----------------------+
                          | - id : int           |
                          | - firstName : String |
                          | - lastName : String  |
                          | - email : String     |
                          +----------------------+
                          | +getDisplayName()    |
                          +----------^-----------+
                                     |
                                     |
                          extends    |
                                     |
                     +---------------+---------------+
                     |                               |
          +----------------------+        +----------------------+
          |       Student        |        |      Trainer         |
          +----------------------+        +----------------------+
          | - batch : String     |        | - department:String  |
          | - active : boolean   |        | - experience : int   |
          +----------------------+        +----------------------+

+--------------------------+
|         Course           |
+--------------------------+
| - id : int               |
| - courseName : String    |
| - description : String   |
| - durationInWeeks : int  |
| - active : boolean       |
+--------------------------+

              1                    *
Student -------------------- Enrollment -------------------- Course
                               (Many-to-Many Relationship)

+-------------------------------+
|         Enrollment            |
+-------------------------------+
| - id : int                    |
| - studentId : int             |
| - courseId : int              |
| - enrollmentDate : LocalDate  |
| - status : String             |
+-------------------------------+

------------------------------------------------------------

+-------------------------+
|     StudentService      |
+-------------------------+
| - List<Student>         |
+-------------------------+
| +addStudent()           |
| +findStudentById()      |
| +updateStudent()        |
| +removeStudent()        |
| +displayStudents()      |
+-------------------------+

+-------------------------+
|      CourseService      |
+-------------------------+
| - List<Course>          |
+-------------------------+
| +addCourse()            |
| +findCourseById()       |
| +updateCourse()         |
| +removeCourse()         |
| +displayCourses()       |
+-------------------------+

+------------------------------+
|     EnrollmentService        |
+------------------------------+
| - List<Enrollment>           |
| - StudentService             |
| - CourseService              |
+------------------------------+
| +enrollStudent()             |
| +completeEnrollment()        |
| +cancelEnrollment()          |
| +displayEnrollments()        |
+------------------------------+

+----------------------+
|     IdGenerator      |
+----------------------+
| <<Utility Class>>    |
| - static counters    |
+----------------------+
| +getNextStudentId()  |
| +getNextCourseId()   |
| +getNextEnrollmentId()|
+----------------------+

+-------------------------------+
|   EntityNotFoundException     |
+-------------------------------+

+-------------------------------+
|    InvalidInputException      |
+-------------------------------+

+----------------------+
|        Main          |
+----------------------+
| Uses all services to |
| provide menu-driven  |
| console application  |
+----------------------+
```

## Relationships

* **Student** inherits from **Person**.
* **Enrollment** connects **Student** and **Course**, representing a many-to-many relationship.
* **StudentService** manages all student-related operations.
* **CourseService** manages all course-related operations.
* **EnrollmentService** manages enrollments and uses both `StudentService` and `CourseService`.
* **IdGenerator** is a utility class that generates unique IDs using static members.
* **Main** acts as the entry point of the application and interacts with all service classes.


