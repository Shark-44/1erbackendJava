package com.datajava.service;

import com.datajava.model.Langage;
import com.datajava.repository.LangageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LangageService {
    @Autowired
    private LangageRepository langageRepository;

    public List<Langage> getAllLangages() {
        return langageRepository.findAll();
    }

    public Langage createLangage(Langage langage) {
        return langageRepository.save(langage);
    }
}
