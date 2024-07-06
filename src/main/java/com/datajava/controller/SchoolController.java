package com.datajava.controller;

import com.datajava.model.School;
import com.datajava.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    public Optional<School> getSchoolById(@PathVariable int id) {
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
}
