package com.datajava.service;

import com.datajava.dto.StudentCreationDTO;
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

    public Student getStudentById(int idStudent) {

        Optional<Student> studentOpt = studentRepository.findById(idStudent);
        if (studentOpt.isPresent()) {

            return studentOpt.get();
        } else {
            throw new StudentNotFoundException("Student not found with id: " + idStudent);
        }
    }

    @Transactional
    public Student createStudent(Student student) {
        
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(int id, Student studentDetails) {
       
        Student student = getStudentById(id);
        student.setName(studentDetails.getName());
        student.setFirstname(studentDetails.getFirstname());
        student.setBirthday(studentDetails.getBirthday());
        student.setPhoto(studentDetails.getPhoto());
        student.setLangages(studentDetails.getLangages());
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(int idStudent) {
        
        Student student = getStudentById(idStudent);

        try {
            // 1. Manage ManyToMany relationship with Langage
            if (!student.getLangages().isEmpty()) {
                for (Langage langage : student.getLangages()) {
                    langage.getStudents().remove(student);
                    langageRepository.save(langage);
                }
                student.getLangages().clear();
            }

            // 2. Manage ManyToOne relationship with School
            School school = student.getSchool();
            if (school != null) {
                school.getStudents().remove(student);
                schoolRepository.save(school);
                student.setSchool(null);
            }

            // 3. Delete the student
            studentRepository.delete(student);
            

        } catch (Exception e) {
            
            throw new StudentDeletionException("Error deleting student: " + e.getMessage());
        }
    }

    public Set<Langage> getLangagesByStudentId(int id) {
        
        Student student = getStudentById(id);
        return student.getLangages();
    }

    public Set<Student> getStudentsByLangageId(int langageId) {
        
        return studentRepository.findAll().stream()
                .filter(student -> student.getLangages().stream()
                        .anyMatch(langage -> langage.getIdLangage() == langageId))
                .collect(Collectors.toSet());
    }

    public School getSchoolByStudentId(int studentId) {
        Student student = getStudentById(studentId);
        return student.getSchool();
    }
}
