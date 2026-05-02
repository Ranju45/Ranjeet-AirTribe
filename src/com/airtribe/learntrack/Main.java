package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        System.out.println(AppConstants.LINE_SEPARATOR);
        System.out.println("  Welcome to " + AppConstants.APP_NAME + " v" + AppConstants.VERSION);
        System.out.println(AppConstants.LINE_SEPARATOR);

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.STUDENT_MANAGEMENT:
                    handleStudentMenu();
                    break;
                case MenuOptions.COURSE_MANAGEMENT:
                    handleCourseMenu();
                    break;
                case MenuOptions.ENROLLMENT_MANAGEMENT:
                    handleEnrollmentMenu();
                    break;
                case MenuOptions.EXIT:
                    System.out.println("\nGoodbye! Exiting " + AppConstants.APP_NAME + ".");
                    running = false;
                    break;
                default:
                    System.out.println("[!] Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    //  Menus

    private static void printMainMenu() {
        System.out.println("\n" + AppConstants.LINE_SEPARATOR);
        System.out.println("  MAIN MENU");
        System.out.println(AppConstants.THIN_SEPARATOR);
        System.out.println("  1. Student Management");
        System.out.println("  2. Course Management");
        System.out.println("  3. Enrollment Management");
        System.out.println("  0. Exit");
        System.out.println(AppConstants.LINE_SEPARATOR);
    }

    private static void handleStudentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("  1. Add new student");
            System.out.println("  2. View all students");
            System.out.println("  3. Search student by ID");
            System.out.println("  4. Deactivate a student");
            System.out.println("  0. Back to main menu");

            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case MenuOptions.ADD_STUDENT:
                        addStudent();
                        break;
                    case MenuOptions.VIEW_ALL_STUDENTS:
                        viewAllStudents();
                        break;
                    case MenuOptions.SEARCH_STUDENT_BY_ID:
                        searchStudentById();
                        break;
                    case MenuOptions.DEACTIVATE_STUDENT:
                        deactivateStudent();
                        break;
                    case MenuOptions.BACK:
                        back = true;
                        break;
                    default:
                        System.out.println("[!] Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("[!] " + e.getMessage());
            }
        }
    }

    private static void handleCourseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("  1. Add new course");
            System.out.println("  2. View all courses");
            System.out.println("  3. Activate / Deactivate a course");
            System.out.println("  0. Back to main menu");

            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case MenuOptions.ADD_COURSE:
                        addCourse();
                        break;
                    case MenuOptions.VIEW_ALL_COURSES:
                        viewAllCourses();
                        break;
                    case MenuOptions.TOGGLE_COURSE_STATUS:
                        toggleCourse();
                        break;
                    case MenuOptions.BACK:
                        back = true;
                        break;
                    default:
                        System.out.println("[!] Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("[!] " + e.getMessage());
            }
        }
    }

    private static void handleEnrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("  1. Enroll a student in a course");
            System.out.println("  2. View enrollments for a student");
            System.out.println("  3. Mark enrollment as completed");
            System.out.println("  4. Mark enrollment as cancelled");
            System.out.println("  0. Back to main menu");

            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case MenuOptions.ENROLL_STUDENT:
                        enrollStudent();
                        break;
                    case MenuOptions.VIEW_ENROLLMENTS_FOR_STUDENT:
                        viewEnrollmentsForStudent();
                        break;
                    case MenuOptions.MARK_ENROLLMENT_COMPLETED:
                        markEnrollmentCompleted();
                        break;
                    case MenuOptions.MARK_ENROLLMENT_CANCELLED:
                        markEnrollmentCancelled();
                        break;
                    case MenuOptions.BACK:
                        back = true;
                        break;
                    default:
                        System.out.println("[!] Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("[!] " + e.getMessage());
            }
        }
    }

    // Student actions

    private static void addStudent() {
        System.out.println("\n-- Add New Student --");
        String firstName = readString("First name: ");
        String lastName  = readString("Last name: ");
        String email     = readString("Email (leave blank to skip): ");
        String batch     = readString("Batch (e.g. Batch-2025): ");

        Student student;
        if (email.trim().isEmpty()) {
            student = studentService.addStudent(firstName, lastName, batch);
        } else {
            student = studentService.addStudent(firstName, lastName, email, batch);
        }
        System.out.println("[+] Student added: " + student);
    }

    private static void viewAllStudents() {
        List<Student> students = studentService.listAllStudents();
        if (students.isEmpty()) {
            System.out.println("  No students found.");
            return;
        }
        System.out.println("\n-- All Students (" + students.size() + ") --");
        for (Student s : students) {
            System.out.println("  " + s);
        }
    }

    private static void searchStudentById() {
        int id = readInt("Enter student ID: ");
        Student student = studentService.findStudentById(id);
        System.out.println("  Found: " + student);
    }

    private static void deactivateStudent() {
        int id = readInt("Enter student ID to deactivate: ");
        studentService.deactivateStudent(id);
        System.out.println("[+] Student ID " + id + " has been deactivated.");
    }

    //  Course actions

    private static void addCourse() {
        System.out.println("\n-- Add New Course --");
        String name        = readString("Course name: ");
        String description = readString("Description (leave blank to skip): ");
        int duration       = readInt("Duration in weeks: ");

        Course course;
        if (description.trim().isEmpty()) {
            course = courseService.addCourse(name, duration);
        } else {
            course = courseService.addCourse(name, description, duration);
        }
        System.out.println("[+] Course added: " + course);
    }

    private static void viewAllCourses() {
        List<Course> courses = courseService.listAllCourses();
        if (courses.isEmpty()) {
            System.out.println("  No courses found.");
            return;
        }
        System.out.println("\n-- All Courses (" + courses.size() + ") --");
        for (Course c : courses) {
            System.out.println("  " + c);
        }
    }

    private static void toggleCourse() {
        int id = readInt("Enter course ID to toggle status: ");
        Course course = courseService.findCourseById(id);
        courseService.toggleCourseStatus(id);
        System.out.println("[+] Course ID " + id + " is now " + (course.isActive() ? "INACTIVE" : "ACTIVE") + ".");
    }

    //  Enrollment actions

    private static void enrollStudent() {
        int studentId = readInt("Enter student ID: ");
        int courseId  = readInt("Enter course ID: ");
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("[+] Enrolled: " + enrollment);
    }

    private static void viewEnrollmentsForStudent() {
        int studentId = readInt("Enter student ID: ");
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("  No enrollments found for student ID " + studentId + ".");
            return;
        }
        System.out.println("\n-- Enrollments for Student ID " + studentId + " --");
        for (Enrollment e : enrollments) {
            System.out.println("  " + e);
        }
    }

    private static void markEnrollmentCompleted() {
        int id = readInt("Enter enrollment ID: ");
        enrollmentService.markAsCompleted(id);
        System.out.println("[+] Enrollment ID " + id + " marked as COMPLETED.");
    }

    private static void markEnrollmentCancelled() {
        int id = readInt("Enter enrollment ID: ");
        enrollmentService.markAsCancelled(id);
        System.out.println("[+] Enrollment ID " + id + " marked as CANCELLED.");
    }

    // Input helpers

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
