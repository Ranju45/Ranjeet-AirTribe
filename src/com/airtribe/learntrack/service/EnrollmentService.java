package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student (ID: " + studentId + ").");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course (ID: " + courseId + ").");
        }
        if (enrollmentRepository.alreadyEnrolled(studentId, courseId)) {
            throw new InvalidInputException("Student " + studentId + " is already enrolled in course " + courseId + ".");
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        studentService.findStudentById(studentId); // validates existence
        return enrollmentRepository.findByStudentId(studentId);
    }

    public void markAsCompleted(int enrollmentId) {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Only ACTIVE enrollments can be marked as completed.");
        }
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void markAsCancelled(int enrollmentId) {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        if (enrollment.getStatus() == EnrollmentStatus.CANCELLED) {
            throw new InvalidInputException("Enrollment is already cancelled.");
        }
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }

    public Enrollment findEnrollmentById(int id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment", id));
    }

    public List<Enrollment> listAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
