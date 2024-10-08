package com.datajava.repository;

import com.datajava.model.Langage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LangageRepository extends JpaRepository<Langage, Integer> {
}
