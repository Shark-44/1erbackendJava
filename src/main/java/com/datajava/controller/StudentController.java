package com.datajava.controller;

import com.datajava.dto.StudentCreationDTO;
import com.datajava.model.Student;
import com.datajava.service.StudentService;
import com.datajava.model.Langage;
import com.datajava.model.School;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.Optional;


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
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
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
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
    // Trouver tout les langages etudié par un étudiant nommé par son id
    @GetMapping("/{id}/langages")
    public Set<Langage> getStudentLangages(@PathVariable int id) {
        return studentService.getLangagesByStudentId(id);
    }
    
    // Chercher dans quel ecole est inscrit un étudiant par id
    @GetMapping("/{id}/school")
    public School getSchoolByStudentId(@PathVariable int id) {
        School school = studentService.getSchoolByStudentId(id);

        if (school == null) {
            throw new RuntimeException("L'étudiant avec l'ID " + id + " n'a pas été trouvé ou n'est pas inscrit dans une école.");
        }

        return school;
    }

}
