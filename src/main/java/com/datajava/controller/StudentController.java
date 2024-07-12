package com.datajava.controller;

import com.datajava.dto.StudentCreationDTO;
import com.datajava.model.Student;
import com.datajava.service.StudentService;
import com.datajava.repository.StudentRepository;

import com.datajava.model.Langage;
import com.datajava.repository.LangageRepository;
import com.datajava.model.School;
import com.datajava.repository.SchoolRepository;
import com.datajava.exception.StudentNotFoundException;
import com.datajava.exception.StudentDeletionException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.HashSet;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private LangageRepository langageRepository;

    @Autowired
    private SchoolRepository schoolRepository;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        logger.info("Received request for student with id: {}", id);
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            logger.info("Found student: {}", student);
            if (student.getLangages() == null) {
                student.setLangages(new HashSet<>());
            }
            return ResponseEntity.ok(student);
        } else {
            logger.warn("Student not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Student not found with id: " + id + ". This is unexpected as the student exists in the general list.");
        }
    }

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/test/{id}")
    public ResponseEntity<?> testGetStudentById(@PathVariable int id) {
        logger.info("Testing direct repository access for student with id: {}", id);
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            logger.info("Found student directly from repository: {}", studentOpt.get());
            return ResponseEntity.ok(studentOpt.get());
        } else {
            logger.warn("Student not found with id: {} (direct repository test)", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Student not found with id: " + id + " (direct repository test)");
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentCreationDTO studentDTO) {
        try {
            Student student = new Student();
            student.setName(studentDTO.getName());
            student.setFirstname(studentDTO.getFirstname());
            student.setBirthday(studentDTO.getBirthday());
            student.setPhoto(studentDTO.getPhoto());
           
            Student createdStudent = studentService.createStudent(student);
            return ResponseEntity.ok(createdStudent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating student: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        logger.info("Received request to delete student with id: {}", id);

        // Vérifier si l'étudiant existe dans la liste générale
        List<Student> allStudents = studentService.getAllStudents();
        boolean studentExists = allStudents.stream().anyMatch(student -> student.getIdStudent() == id);

        if (!studentExists) {
            logger.warn("Student not found in general list with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id: " + id);
        }

        // Vérifier et gérer les liaisons avec Langage
        Optional<Student> studentOpt = studentService.getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();

            if (student.getLangages() != null && !student.getLangages().isEmpty()) {
                student.getLangages().forEach(langage -> {
                    langage.getStudents().remove(student);
                    langageRepository.save(langage);
                });
                student.setLangages(null);
            }

            // Vérifier et gérer les liaisons avec School
            if (student.getSchool() != null) {
                School school = student.getSchool();
                school.getStudents().remove(student);
                schoolRepository.save(school);
                student.setSchool(null);
            }

            // Supprimer l'étudiant
            studentService.deleteStudent(id);
            return ResponseEntity.ok().body("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found with id: " + id);
        }
    }

    @GetMapping("/{id}/langages")
    public Set<Langage> getStudentLangages(@PathVariable int id) {
        return studentService.getLangagesByStudentId(id);
    }
    
    @GetMapping("/{id}/school")
    public School getSchoolByStudentId(@PathVariable int id) {
        School school = studentService.getSchoolByStudentId(id);

        if (school == null) {
            throw new RuntimeException("L'étudiant avec l'ID " + id + " n'a pas été trouvé ou n'est pas inscrit dans une école.");
        }

        return school;
    }
}
