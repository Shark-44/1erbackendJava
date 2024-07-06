package com.datajava.service;

import com.datajava.model.Student;
import com.datajava.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

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

    public void deleteStudent(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }
}
