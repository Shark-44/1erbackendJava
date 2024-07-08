package com.datajava.controller;

import com.datajava.model.Langage;
import com.datajava.model.School;
import com.datajava.model.Student;
import com.datajava.service.SchoolService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/schools")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable int id) {
        return schoolService.getSchoolById(id);
    }

    @PostMapping
    public School createSchool(@RequestBody School school) {
        return schoolService.createSchool(school);
    }

    @PutMapping("/{id}")
    public School updateSchool(@PathVariable int id, @RequestBody School schoolDetails) {
        return schoolService.updateSchool(id, schoolDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable int id) {
        schoolService.deleteSchool(id);
    }
    // Trouver tout les langages enseignés par une école nommé par id
    @GetMapping("/{id}/langages")
    public Set<Langage> getLangagesBySchoolId(@PathVariable int id) {
        return schoolService.getLangagesBySchoolId(id);
    }
    // trouver tout les éleves d'une école
    @GetMapping("/{id}/students")
    public Set<Student> getStudentsBySchoolId(@PathVariable int id) {
        return schoolService.getStudentsBySchoolId(id);
    }
}
