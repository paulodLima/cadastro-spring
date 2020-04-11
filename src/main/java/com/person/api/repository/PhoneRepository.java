package com.person.api.repository;

import com.person.api.model.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {


    @Transactional
    @Modifying
    @Query( value = "DELETE FROM DBPERSON.TB_PHONE tb WHERE tb.DOCUMENT_NUMBER = :documentNumber",nativeQuery = true)
    void deleteByCpf(@Param("documentNumber") String documentNumber);
}
