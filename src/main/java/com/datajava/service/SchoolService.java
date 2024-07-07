package com.datajava.service;

import com.datajava.model.Langage;
import com.datajava.model.Student;
import com.datajava.model.School;

import com.datajava.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(int id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
    }

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public School updateSchool(int id, School schoolDetails) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
        school.setNameSchool(schoolDetails.getNameSchool());
        school.setPhotoSchool(schoolDetails.getPhotoSchool());
        return schoolRepository.save(school);
    }

    public void deleteSchool(int id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
        schoolRepository.delete(school);
    }

    public Set<Langage> getLangagesBySchoolId(int id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
        return school.getLangages();
    }

    public Set<Student> getStudentsBySchoolId(int schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new RuntimeException("School not found"));
        return school.getStudents();
    }
}
