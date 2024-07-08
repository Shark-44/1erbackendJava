package com.datajava.controller;

import com.datajava.model.Student;
import com.datajava.model.Langage;
import com.datajava.model.School;
import com.datajava.service.StudentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
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
