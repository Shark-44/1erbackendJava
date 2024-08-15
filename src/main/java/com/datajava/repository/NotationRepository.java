package com.datajava.repository;

import com.datajava.model.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//step 1
public interface NotationRepository extends JpaRepository<Notation, Integer> {
    List<Notation> findBySchool_IdSchool(int schoolId);
//step 2
@Query("SELECT n FROM Notation n WHERE n.school.id = :schoolId ORDER BY n.student.id, n.langage.id")
    List<Notation> findAllBySchoolIdOrderByStudentAndLangage(@Param("schoolId") int schoolId);

}
