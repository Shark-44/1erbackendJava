package com.datajava.service;

import com.datajava.model.Test;
import com.datajava.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }
}
