package com.person.api.repository;

import com.person.api.model.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface OperatorRepository extends JpaRepository<OperatorEntity, Long> {

    @Transactional
    @Query( value = "SELECT ps FROM OperatorEntity ps where ps.login  = :name")
    OperatorEntity fiddlyOperatorName(String name);
}
