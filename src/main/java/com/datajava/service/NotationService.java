package com.datajava.service;

import com.datajava.model.Notation;
import com.datajava.model.Student;
import com.datajava.repository.NotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotationService {

    @Autowired
    private NotationRepository notationRepository;

    public List<Map<String, Object>> calculateAverageBySchool(int schoolId) {
        List<Notation> notations = notationRepository.findBySchool_IdSchool(schoolId);
        Map<Student, Double> averages = notations.stream()
                .collect(Collectors.groupingBy(
                        Notation::getStudent,
                        Collectors.averagingDouble(Notation::getNote)
                ));
        
        return averages.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("studentId", entry.getKey().getIdStudent());
                    result.put("studentName", entry.getKey().getName());
                    result.put("average", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }
}
