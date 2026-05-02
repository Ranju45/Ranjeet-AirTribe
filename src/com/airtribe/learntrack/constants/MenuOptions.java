package com.airtribe.learntrack.constants;

public class MenuOptions {

    private MenuOptions() {}

    // Main menu
    public static final int STUDENT_MANAGEMENT = 1;
    public static final int COURSE_MANAGEMENT = 2;
    public static final int ENROLLMENT_MANAGEMENT = 3;
    public static final int EXIT = 0;

    // Student sub-menu
    public static final int ADD_STUDENT = 1;
    public static final int VIEW_ALL_STUDENTS = 2;
    public static final int SEARCH_STUDENT_BY_ID = 3;
    public static final int DEACTIVATE_STUDENT = 4;
    public static final int BACK = 0;

    // Course sub-menu
    public static final int ADD_COURSE = 1;
    public static final int VIEW_ALL_COURSES = 2;
    public static final int TOGGLE_COURSE_STATUS = 3;

    // Enrollment sub-menu
    public static final int ENROLL_STUDENT = 1;
    public static final int VIEW_ENROLLMENTS_FOR_STUDENT = 2;
    public static final int MARK_ENROLLMENT_COMPLETED = 3;
    public static final int MARK_ENROLLMENT_CANCELLED = 4;
}
