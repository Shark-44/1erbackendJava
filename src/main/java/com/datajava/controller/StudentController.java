package com.datajava.controller;

import com.datajava.model.Student;
import com.datajava.model.Langage;
import com.datajava.service.StudentService;
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
    @GetMapping("/{id}/langages")
    public Set<Langage> getStudentLangages(@PathVariable int id) {
        return studentService.getLangagesByStudentId(id);
    }
    @GetMapping("/students/langages")
    public Set<Langage> getLangagesByStudentId(@RequestParam int studentId) {
        return studentService.getLangagesByStudentId(studentId);
    }
    @GetMapping("/{langageId}/students")
    public Set<Student> getStudentsByLangageId(@PathVariable int langageId) {
        return studentService.getStudentsByLangageId(langageId);
    }
}
