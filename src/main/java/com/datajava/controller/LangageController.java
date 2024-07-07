package com.datajava.controller;


import com.datajava.dto.SchoolDTO;
import com.datajava.model.Student;
import com.datajava.model.Langage;
import com.datajava.service.LangageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@RestController
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
    
    @PostMapping
    public Langage createLangage(@RequestBody Langage langage) {
        return langageService.createLangage(langage);
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
