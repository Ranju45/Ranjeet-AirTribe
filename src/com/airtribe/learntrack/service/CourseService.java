package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository = new CourseRepository();

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        InputValidator.validateNotEmpty(courseName, "Course name");
        InputValidator.validatePositive(durationInWeeks, "Duration");

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courseRepository.save(course);
        return course;
    }

    // Overloaded — without description
    public Course addCourse(String courseName, int durationInWeeks) {
        return addCourse(courseName, "", durationInWeeks);
    }

    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));
    }

    public void toggleCourseStatus(int id) {
        Course course = findCourseById(id);
        course.setActive(!course.isActive());
    }

    public int getTotalCourses() {
        return courseRepository.count();
    }
}
