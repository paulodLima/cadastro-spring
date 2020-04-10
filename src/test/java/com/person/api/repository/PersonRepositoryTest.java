package com.person.api.repository;

import com.person.api.model.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;


    @Test
    void findByCpf() {

        Optional<PersonEntity> byCpf = personRepository.findByCpf("52278512048");

        assertTrue(byCpf.isPresent());

        PersonEntity personEntity = byCpf.get();

        assertEquals("52278512048", personEntity.getDocumentNumber());
        assertEquals("brincante da silva", personEntity.getFullName());
        assertEquals(101, personEntity.getId());

    }


    @Test
    void findByCpfNotFound() {

        Optional<PersonEntity> byCpf = personRepository.findByCpf("77777777711");
        assertFalse(byCpf.isPresent());


    }
}