package com.datajava.controller;

import com.datajava.service.NotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.1.157:4200")
@RequestMapping("/api/notations")
public class NotationController {

    @Autowired
    private NotationService notationService;
    // step 1
    @GetMapping("/average/{schoolId}")
    public List<Map<String, Object>> getAverageBySchool(@PathVariable int schoolId) {
        return notationService.calculateAverageBySchool(schoolId);
    }
    // step 2
    @GetMapping("/school/{schoolId}/notations")
    public List<Map<String, Object>> getNotationsBySchoolId(@PathVariable int schoolId) {
        return notationService.getNotationsBySchoolId(schoolId);
    }
}
