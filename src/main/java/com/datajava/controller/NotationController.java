package com.datajava.controller;

import com.datajava.service.NotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/notations")
public class NotationController {

    @Autowired
    private NotationService notationService;

    @GetMapping("/average/{schoolId}")
    public List<Map<String, Object>> getAverageBySchool(@PathVariable int schoolId) {
        return notationService.calculateAverageBySchool(schoolId);
    }
}
