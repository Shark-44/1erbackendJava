package com.datajava.service;

import com.datajava.model.Student;
import com.datajava.controller.StudentController;
import com.datajava.repository.StudentRepository;
import com.datajava.model.Langage;
import com.datajava.repository.LangageRepository;
import com.datajava.model.School;
import com.datajava.repository.SchoolRepository;
import com.datajava.exception.StudentNotFoundException;
import com.datajava.exception.StudentDeletionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LangageRepository langageRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        logger.info("Fetching student with id {}: {}", id, student);
        return student;
    }

    @Transactional
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setFirstname(studentDetails.getFirstname());
        student.setBirthday(studentDetails.getBirthday());
        student.setPhoto(studentDetails.getPhoto());
        student.setLangages(studentDetails.getLangages());
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        try {
            // 1. Gérer la relation ManyToMany avec Langage
            if (!student.getLangages().isEmpty()) {
                for (Langage langage : student.getLangages()) {
                    langage.getStudents().remove(student);
                    langageRepository.save(langage);
                }
                student.getLangages().clear();
            }

            // 2. Gérer la relation ManyToOne avec School
            School school = student.getSchool();
            if (school != null) {
                school.getStudents().remove(student);
                schoolRepository.save(school);
                student.setSchool(null);
            }

            // 3. Supprimer l'étudiant
            studentRepository.delete(student);

        } catch (Exception e) {
            throw new StudentDeletionException("Error deleting student: " + e.getMessage());
        }
    }

    public Set<Langage> getLangagesByStudentId(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return student.getLangages();
    }

    public Set<Student> getStudentsByLangageId(int langageId) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getLangages().stream()
                        .anyMatch(langage -> langage.getIdLangage() == langageId))
                .collect(Collectors.toSet());
    }

    public School getSchoolByStudentId(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student == null) {
            return null;
        }

        return student.getSchool();
    }
}
