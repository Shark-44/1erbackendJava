package com.datajava.service;

import com.datajava.model.Langage;
import com.datajava.repository.LangageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LangageService {
    @Autowired
    private LangageRepository langageRepository;

    public List<Langage> getAllLangages() {
        return langageRepository.findAll();
    }

    public Optional<Langage> getLangageById(int id) {
        return langageRepository.findById(id);
    }

    public Langage createLangage(Langage langage) {
        return langageRepository.save(langage);
    }
    
    public Langage updateLangage(int id, Langage langageDetails) {
        Langage langage = langageRepository.findById(id).orElseThrow(() -> new RuntimeException("Langage not found"));
        langage.setNameLangage(langageDetails.getNameLangage());
        return langageRepository.save(langage);
    }

    public void deleteLangage(int id) {
        Langage langage = langageRepository.findById(id).orElseThrow(() -> new RuntimeException("Langage not found"));
        langageRepository.delete(langage);
    }
}
