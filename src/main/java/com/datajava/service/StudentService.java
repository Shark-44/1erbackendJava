package com.datajava.service;

import com.datajava.model.Student;
import com.datajava.model.Langage;
import com.datajava.model.School;

import com.datajava.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

