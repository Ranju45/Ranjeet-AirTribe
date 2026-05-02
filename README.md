# LearnTrack — Student & Course Management System

A console-based Java application to manage students, courses, and enrollments.
Built as a Core Java fundamentals project for the Airtribe cohort.

---

## How to Compile and Run

### Prerequisites
- JDK 11 or above installed
- Terminal / Command Prompt

### Steps

```bash
# 1. Navigate to the project root
cd LearnTrack

# 2. Compile all source files
javac -d out -sourcepath src src/com/airtribe/learntrack/Main.java

# 3. Run the application
java -cp out com.airtribe.learntrack.Main
```

> Tip: If using IntelliJ IDEA or Eclipse, simply import the project,
> mark `src/` as the Sources Root, and run `Main.java`.

---

## Project Structure

```
src/
└── com/airtribe/learntrack/
    ├── Main.java                      ← Entry point, menu loop
    ├── entity/                        ← Data model classes
    │   ├── Person.java
    │   ├── Student.java
    │   ├── Course.java
    │   └── Enrollment.java
    ├── repository/                    ← In-memory ArrayList storage
    ├── service/                       ← Business logic
    ├── exception/                     ← Custom exceptions
    ├── util/                          ← IdGenerator, InputValidator
    ├── constants/                     ← AppConstants, MenuOptions
    └── enums/                         ← EnrollmentStatus, CourseStatus
docs/
    ├── Setup_Instructions.md
    ├── JVM_Basics.md
    └── Design_Notes.md
```

---

## Features

**Student Management**
- Add student (with or without email)
- View all students
- Search student by ID
- Deactivate a student

**Course Management**
- Add course (with or without description)
- View all courses
- Toggle course active/inactive status

**Enrollment Management**
- Enroll a student in a course
- View all enrollments for a student
- Mark enrollment as COMPLETED or CANCELLED
 
## Repository 
https://github.com/Ranju45/Ranjeet-AirTribe 
