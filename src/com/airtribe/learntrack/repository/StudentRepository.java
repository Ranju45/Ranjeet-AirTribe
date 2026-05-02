package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Optional<Student> findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return Optional.of(s);
        }
        return Optional.empty();
    }

    public boolean deleteById(int id) {
        return students.removeIf(s -> s.getId() == id);
    }

    public int count() {
        return students.size();
    }
}
