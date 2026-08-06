Why you used ArrayList instead of array?

Because we have multiple students, cources, and enrollments, we dont have fixed size And we get the arraylist methods accessible like add(). remove() etc.
this makes esier.

Where you used static members and why?
I used static variable inside the IDGenerater class where i have initilized the studentId , courseId and enrollmentId as we have to do not want override values. 

I also used static methods:

```java
IdGenerator.getNextStudentId();
IdGenerator.getNextCourseId();
IdGenerator.getNextEnrollmentId();
```

Using static methods allows IDs to be generated without creating an object of the `IdGenerator` class.

This ensures that every student, course, and enrollment receives a unique ID.

Where I Used Inheritance and What I Gained
I used inheritance by creating a **Person** class and extending it with the **Student** class.

```
Person
   │
   ▼
Student
```

The `Person` class contains common fields:

- id
- firstName
- lastName
- email

The `Student` class inherits these fields and adds its own fields:

- batch
- active

I also used the `super()` keyword to call the constructor of the parent class and overrode the `getDisplayName()` method to demonstrate method overriding.

### Benefits of Inheritance

- Avoids duplicate code.
- Improves code reusability.
- Makes the project easier to maintain.
- Demonstrates the "is-a" relationship (`Student` is a `Person`).
- Helps implement Object-Oriented Programming principles.
