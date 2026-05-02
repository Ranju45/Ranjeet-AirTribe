package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository = new StudentRepository();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        InputValidator.validateNotEmpty(firstName, "First name");
        InputValidator.validateNotEmpty(lastName, "Last name");
        InputValidator.validateNotEmpty(batch, "Batch");
        InputValidator.validateEmail(email);

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        studentRepository.save(student);
        return student;
    }

    // Overloaded — without email
    public Student addStudent(String firstName, String lastName, String batch) {
        InputValidator.validateNotEmpty(firstName, "First name");
        InputValidator.validateNotEmpty(lastName, "Last name");
        InputValidator.validateNotEmpty(batch, "Batch");

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        studentRepository.save(student);
        return student;
    }

    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        if (!student.isActive()) {
            throw new InvalidInputException("Student with ID " + id + " is already inactive.");
        }
        student.setActive(false);
    }

    public void updateStudentEmail(int id, String newEmail) {
        InputValidator.validateEmail(newEmail);
        Student student = findStudentById(id);
        student.setEmail(newEmail);
    }

    public int getTotalStudents() {
        return studentRepository.count();
    }
}
