package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public void save(Course course) {
        courses.add(course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) return Optional.of(c);
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        return courses.removeIf(c -> c.getId() == id);
    }

    public int count() {
        return courses.size();
    }
}
