package com.datajava.repository;

import com.datajava.model.Notation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotationRepository extends JpaRepository<Notation, Integer> {
}
