package com.datajava;

import com.datajava.model.School;
import com.datajava.model.Student;
import com.datajava.repository.SchoolRepository;
import com.datajava.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void testSaveAndFetchStudent() {
        School school = new School();
        school.setNameSchool("Test School");
        schoolRepository.save(school);

        Student student = new Student();
        student.setFirstname("John");
        student.setName("Doe");
        student.setSchool(school);
        studentRepository.save(student);

        Student fetchedStudent = studentRepository.findById(student.getIdStudent()).orElse(null);
        assertNotNull(fetchedStudent);
        assertEquals("Test School", fetchedStudent.getSchool().getNameSchool());
    }
}
