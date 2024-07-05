package com.datajava.controller;

import com.datajava.model.Langage;
import com.datajava.service.LangageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/langages")
public class LangageController {
    @Autowired
    private LangageService langageService;

    @GetMapping
    public List<Langage> getAllLangages() {
        return langageService.getAllLangages();
    }

    @PostMapping
    public Langage createLangage(@RequestBody Langage langage) {
        return langageService.createLangage(langage);
    }
}
