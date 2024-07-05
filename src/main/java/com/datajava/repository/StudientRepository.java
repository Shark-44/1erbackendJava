package com.datajava.repository;

import com.datajava.model.Studient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudientRepository extends JpaRepository<Studient, Integer> {
}
