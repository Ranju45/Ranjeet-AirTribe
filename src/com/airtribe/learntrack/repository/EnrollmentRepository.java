package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public Optional<Enrollment> findById(int id) {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) return Optional.of(e);
        }
        return Optional.empty();
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) result.add(e);
        }
        return result;
    }

    public boolean alreadyEnrolled(int studentId, int courseId) {
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId && e.getCourseId() == courseId) return true;
        }
        return false;
    }

    public int count() {
        return enrollments.size();
    }
}
