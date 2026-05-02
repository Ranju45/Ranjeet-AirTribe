package com.airtribe.learntrack.util;

public class IdGenerator {

    private static int studentIdCounter = 0;
    private static int courseIdCounter = 0;
    private static int enrollmentIdCounter = 0;

    // Prevent instantiation
    private IdGenerator() {}

    public static int getNextStudentId() {
        return ++studentIdCounter;
    }

    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }

    public static void resetAll() {
        studentIdCounter = 0;
        courseIdCounter = 0;
        enrollmentIdCounter = 0;
    }
}
