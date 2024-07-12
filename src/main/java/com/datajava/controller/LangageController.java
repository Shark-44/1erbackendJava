package com.datajava.controller;


import com.datajava.dto.LangageCreationDTO;
import com.datajava.dto.SchoolDTO;
import com.datajava.model.Student;
import com.datajava.model.Langage;
import com.datajava.service.LangageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/langages")
public class LangageController {
    @Autowired
    private LangageService langageService;

    @GetMapping
    public List<Langage> getAllLangages() {
        return langageService.getAllLangages();
    }
    
    @GetMapping("/{id}")
    public Optional<Langage> getLangageById(@PathVariable int id) {
        return langageService.getLangageById(id);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Langage> createLangage(@RequestBody LangageCreationDTO langageDTO, HttpServletRequest request) {
        Langage langage = new Langage();
        langage.setNameLangage(langageDTO.getNameLangage());
        Langage createdLangage = langageService.createLangage(langage);
        return ResponseEntity.ok(createdLangage);
    }
    
    @PutMapping("/{id}")
    public Langage updateLangage(@PathVariable int id, @RequestBody Langage langageDetails) {
        return langageService.updateLangage(id, langageDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteLangage(@PathVariable int id) {
        langageService.deleteLangage(id);
    }
    //trouver tout les étudiants apprenant un langage nommé par id
    @GetMapping("/{langageId}/students")
    public Set<Student> getStudentsByLangageId(@PathVariable int langageId) {
        return langageService.getStudentsByLangageId(langageId);
    }
    /*@GetMapping("/{langageId}/schools")
    public Set<School> getSchoolsByLangageId(@PathVariable int langageId) {
        return langageService.getSchoolsByLangageId(langageId);
    }*/

    // trouver toutes le écoles enseignant un langage nommé par id
    @GetMapping("/{langageId}/schools")
    public Set<SchoolDTO> getSchoolsByLangageId(@PathVariable int langageId) {
        return langageService.getSchoolsByLangageId(langageId);
    }
}