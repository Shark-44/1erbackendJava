package com.datajava.service;

import com.datajava.model.Student;
import com.datajava.repository.StudentRepository;
import com.datajava.model.Langage;
import com.datajava.repository.LangageRepository;
import com.datajava.model.School;
import com.datajava.repository.SchoolRepository;
import com.datajava.exception.StudentNotFoundException;
import com.datajava.dto.SchoolCreationDTO; 
import com.datajava.dto.StudentUpdateDTO;
import com.datajava.exception.StudentDeletionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

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

    // Commenté pour éviter la confusion, car il y a déjà une méthode updateStudent
    /*
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
    */

    @Transactional
    public void deleteStudent(int idStudent) {
        Student student = getStudentById(idStudent);

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

    @Transactional
    public Student updateStudentBasicInfo(int idStudent, StudentUpdateDTO studentDTO) {
        Student student = getStudentById(idStudent);
        student.setName(studentDTO.getName());
        student.setFirstname(studentDTO.getFirstname());
        student.setBirthday(studentDTO.getBirthday());
        student.setPhoto(studentDTO.getPhoto());
        return studentRepository.save(student);
    }

    @Transactional
    public Student associateSchoolToStudent(int studentId, int schoolId) {
        Student student = getStudentById(studentId);
        School school = schoolRepository.findById(schoolId)
            .orElseThrow(() -> new RuntimeException("School not found with id: " + schoolId));
        
        student.setSchool(school);
        return studentRepository.save(student);
    }

    @Transactional
    public Student associateLangagesToStudent(int studentId, List<Integer> langageIds, int schoolId) {
        Student student = getStudentById(studentId);
        
        // Associer l'école
        School school = schoolRepository.findById(schoolId)
            .orElseThrow(() -> new RuntimeException("School not found with id: " + schoolId));
        student.setSchool(school);
        
        // Associer les langages
        Set<Langage> langages = langageIds.stream()
            .map(id -> langageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Langage not found with id: " + id)))
            .collect(Collectors.toSet());
        
        student.setLangages(langages);
        
        return studentRepository.save(student);
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

    public SchoolCreationDTO getSchoolByStudentId(int idStudent) {
        Student student = getStudentById(idStudent);
        School school = student.getSchool();

        if (school == null) {
            throw new StudentNotFoundException("School not found for student id: " + idStudent);
        }

        // Créer et retourner un SchoolCreationDTO
        SchoolCreationDTO schoolDTO = new SchoolCreationDTO();
        schoolDTO.setIdSchool(school.getIdSchool());
        schoolDTO.setNameSchool(school.getNameSchool());
        schoolDTO.setPhotoSchool(school.getPhotoSchool());

        return schoolDTO;
    }
}