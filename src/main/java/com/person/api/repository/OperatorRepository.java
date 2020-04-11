package com.person.api.repository;

import com.person.api.model.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface OperatorRepository extends JpaRepository<OperatorEntity, Long> {

    @Transactional
    @Query( value = "SELECT ps FROM OperatorEntity ps where ps.login  = :name")
    OperatorEntity fiddlyOperatorName(String name);

    @Transactional
    @Query( value = "SELECT ps FROM OperatorEntity ps where ps.login  = :name")
    Optional <OperatorEntity> findOperatorName(String name);

    @Transactional
    @Modifying
    @Query( value = "DELETE FROM OperatorEntity ps where ps.login  = :login")
    void deleteByLogin(@Param("login") String documentNumber);
}
