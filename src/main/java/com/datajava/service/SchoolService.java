package com.datajava.service;

import com.datajava.model.School;
import com.datajava.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(int id) {
        return schoolRepository.findById(id);
    }

    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    public School updateSchool(int id, School schoolDetails) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
        school.setNameSchool(schoolDetails.getNameSchool());
        school.setPhotoSchool(schoolDetails.getPhotoSchool());
        return schoolRepository.save(school);
    }

    public void deleteSchool(int id) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("School not found"));
        schoolRepository.delete(school);
    }
}
