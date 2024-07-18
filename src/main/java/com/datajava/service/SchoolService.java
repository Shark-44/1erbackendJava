package com.datajava.service;

import com.datajava.dto.SchoolCreationDTO;
import com.datajava.model.Langage;
import com.datajava.model.Student;
import com.datajava.model.School;
import com.datajava.repository.SchoolRepository;
import com.datajava.repository.LangageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private LangageRepository langageRepository;

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(int id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
    }

    @Transactional
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    @Transactional
    public School updateSchool(int schoolId, SchoolCreationDTO schoolDTO) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found with id: " + schoolId));

        school.setNameSchool(schoolDTO.getNameSchool());
        school.setPhotoSchool(schoolDTO.getPhotoSchool());

        return schoolRepository.save(school);
    }

    @Transactional
    public School associateLangagesToSchool(int schoolId, List<Integer> langageIds) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found with id: " + schoolId));

        Set<Langage> langages = langageIds.stream()
                .map(id -> langageRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Langage not found with id: " + id)))
                .collect(Collectors.toSet());

        school.getLangages().addAll(langages);

        // Mettre à jour l'association inverse pour chaque langage
        langages.forEach(langage -> langage.getSchools().add(school));

        schoolRepository.save(school);
        langages.forEach(langageRepository::save);

        return school;
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
