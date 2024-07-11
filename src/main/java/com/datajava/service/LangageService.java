package com.datajava.service;

import com.datajava.model.School; 
import com.datajava.dto.SchoolDTO;
import com.datajava.model.Student;
import com.datajava.service.LangageService;
import com.datajava.model.Langage;
import com.datajava.repository.LangageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class LangageService {
    @Autowired
    private LangageRepository langageRepository;

    public List<Langage> getAllLangages() {
        return langageRepository.findAll();
    }

    public Optional<Langage> getLangageById(int langageId) {
        return langageRepository.findById(langageId);
    }

    public Langage createLangage(Langage langage) {
        return langageRepository.save(langage);
    }
    
    public Langage updateLangage(int id, Langage langageDetails) {
        Langage langage = langageRepository.findById(id).orElseThrow(() -> new RuntimeException("Langage not found"));
        langage.setNameLangage(langageDetails.getNameLangage());
        return langageRepository.save(langage);
    }

    public void deleteLangage(int id) {
        Langage langage = langageRepository.findById(id).orElseThrow(() -> new RuntimeException("Langage not found"));
        langageRepository.delete(langage);
    }
    public Set<Student> getStudentsByLangageId(int langageId) {
        Langage langage = langageRepository.findById(langageId)
                .orElseThrow(() -> new RuntimeException("Langage not found"));
        return langage.getStudents();
    }
    /*public Set<School> getSchoolsByLangageId(int langageId) {
        Langage langage = langageRepository.findById(langageId)
                .orElseThrow(() -> new RuntimeException("Langage not found"));
        return langage.getSchools();
    } INUTILE  avec DTO*/

    public Set<SchoolDTO> getSchoolsByLangageId(int langageId) {
        Langage langage = langageRepository.findById(langageId)
                .orElseThrow(() -> new RuntimeException("Langage not found"));
        return langage.getSchools().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    private SchoolDTO convertToDTO(School school) {
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setIdSchool(school.getIdSchool());
        schoolDTO.setNameSchool(school.getNameSchool());
        schoolDTO.setPhotoSchool(school.getPhotoSchool());
        return schoolDTO;
    }
}