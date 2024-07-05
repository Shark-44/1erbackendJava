package com.datajava.service;

import com.datajava.model.Studient;
import com.datajava.repository.StudientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudientService {
    @Autowired
    private StudientRepository studientRepository;

    public List<Studient> getAllStudients() {
        return studientRepository.findAll();
    }

    public Studient createStudient(Studient studient) {
        return studientRepository.save(studient);
    }
}
