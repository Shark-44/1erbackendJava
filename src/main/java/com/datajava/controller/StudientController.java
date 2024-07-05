package com.datajava.controller;

import com.datajava.model.Studient;
import com.datajava.service.StudientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studients")
public class StudientController {
    @Autowired
    private StudientService studientService;

    @GetMapping
    public List<Studient> getAllStudients() {
        return studientService.getAllStudients();
    }

    @PostMapping
    public Studient createStudient(@RequestBody Studient studient) {
        return studientService.createStudient(studient);
    }
}
