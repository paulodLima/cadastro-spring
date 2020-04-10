package com.person.api.repository;

import com.person.api.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    @Query( value = "SELECT ps FROM PersonEntity ps where ps.documentNumber  = :documentNumber")
    Optional<PersonEntity> findByCpf(@Param("documentNumber") String documentNumber);

    @Transactional
    @Modifying
    @Query( value = "DELETE FROM PersonEntity ps where ps.documentNumber  = :documentNumber")
    void deleteByCpf(@Param("documentNumber") String documentNumber);
}

