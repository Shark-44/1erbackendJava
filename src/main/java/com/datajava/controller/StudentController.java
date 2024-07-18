package com.datajava.controller;

import com.datajava.dto.SchoolDTO;
import com.datajava.dto.StudentCreationDTO;
import com.datajava.dto.StudentUpdateDTO;
import com.datajava.model.Student;
import com.datajava.service.StudentService;
import com.datajava.model.Langage;
import com.datajava.exception.StudentNotFoundException;
import com.datajava.exception.StudentDeletionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
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
    
    // PUT sous condition
    @PutMapping("/{id}/basic-info")
    public ResponseEntity<?> updateStudentBasicInfo(@PathVariable int id, @Valid @RequestBody StudentUpdateDTO studentDTO) {
        try {
            Student updatedStudent = studentService.updateStudentBasicInfo(id, studentDTO);
            return ResponseEntity.ok(updatedStudent);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error updating student basic info: " + e.getMessage());
        }
    }
    @PutMapping("/{id}/associate-school/{schoolId}")
    public ResponseEntity<?> associateSchoolToStudent(@PathVariable int id, @PathVariable int schoolId) throws StudentNotFoundException {
        try {
            Student updatedStudent = studentService.associateSchoolToStudent(id, schoolId);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error associating school to student: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{id}/associate-langages")
    public ResponseEntity<?> associateLangagesToStudent(@PathVariable int id, 
                                                        @RequestParam List<Integer> langageIds, 
                                                        @RequestParam int schoolId) throws StudentNotFoundException {
        try {
            Student updatedStudent = studentService.associateLangagesToStudent(id, langageIds, schoolId);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error associating langages to student: " + e.getMessage());
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
            SchoolDTO schoolDTO = studentService.getSchoolByStudentId(id);
            return ResponseEntity.ok(schoolDTO);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}