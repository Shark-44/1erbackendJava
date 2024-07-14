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
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{idStudent}")
    public ResponseEntity<?> getStudentById(@PathVariable int idStudent) {
        try {
            Student student = studentService.getStudentById(idStudent);
            if (student == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving student");
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
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating student: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentService.updateStudent(id, studentDetails);
            return ResponseEntity.ok(updatedStudent);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating student: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
       
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok().body("Student deleted successfully");
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (StudentDeletionException e) {
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/langages")
    public ResponseEntity<?> getStudentLangages(@PathVariable int id) {
        try {
            Set<Langage> langages = studentService.getLangagesByStudentId(id);
            return ResponseEntity.ok(langages);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}/school")
    public ResponseEntity<?> getSchoolByStudentId(@PathVariable int id) {
        try {
            School school = studentService.getSchoolByStudentId(id);
            return ResponseEntity.ok(school);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}