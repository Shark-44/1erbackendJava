package com.datajava.controller;

import com.datajava.dto.SchoolCreationDTO;
import com.datajava.model.Langage;
import com.datajava.model.School;
import com.datajava.model.Student;
import com.datajava.service.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://192.168.1.157:4200") //avant 5173
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

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createSchool(@Valid @RequestBody SchoolCreationDTO schoolCreationDTO) {
        try {
            School school = new School();
            school.setNameSchool(schoolCreationDTO.getNameSchool());
            school.setPhotoSchool(schoolCreationDTO.getPhotoSchool());

            School createdSchool = schoolService.createSchool(school);
            return ResponseEntity.ok(createdSchool);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating school: " + e.getMessage());
        }
    }

    // PUT
    @PutMapping("/{schoolId}")
    public ResponseEntity<?> updateSchool(@PathVariable int schoolId, @Valid @RequestBody SchoolCreationDTO schoolDTO) {
        try {
            School updatedSchool = schoolService.updateSchool(schoolId, schoolDTO);
            return ResponseEntity.ok(updatedSchool);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error updating school: " + e.getMessage());
        }
    }

    @PutMapping("/{schoolId}/associate-langages")
    public ResponseEntity<?> associateLangagesToSchool(@PathVariable int schoolId, 
                                                    @RequestParam List<Integer> langageIds) {
        try {
            School updatedSchool = schoolService.associateLangagesToSchool(schoolId, langageIds);
            return ResponseEntity.ok(updatedSchool);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error associating langages to school: " + e.getMessage());
        }
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
