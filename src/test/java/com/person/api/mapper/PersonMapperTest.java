package com.person.api.mapper;

import com.person.api.dto.PersonRequestDTO;
import com.person.api.dto.PersonResponseDTO;
import com.person.api.model.PersonEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;

class PersonMapperTest {

    private final PersonMapper mapper = Mappers.getMapper(PersonMapper.class);

    @Test
    void toEntity() {

        PersonRequestDTO personRequestDTO = spy(PersonRequestDTO.class);

        personRequestDTO.setFullName("Alguem da silva");
        personRequestDTO.setBirthDate(LocalDate.parse("1997-08-27"));
        personRequestDTO.setDocumentNumber("879.987.987-99");

        PersonEntity personEntity = mapper.toEntity(personRequestDTO);

        assertEquals("Alguem da silva", personEntity.getFullName());
        assertEquals(LocalDate.parse("1997-08-27"), personEntity.getBirthDate());
        assertEquals("879.987.987-99", personEntity.getDocumentNumber());

    }

    @Test
    void toEntityWithParamNull() {


        PersonEntity personEntity = mapper.toEntity(null);

        assertNull(personEntity);

    }

    @Test
    void toResponseWithAddress() {

        PersonEntity personEntity = spy(PersonEntity.class);

        personEntity.setFullName("Alguem da silva");
        personEntity.setBirthDate(LocalDate.parse("1997-08-27"));
        personEntity.setDocumentNumber("879.987.987-99");

        PersonResponseDTO personResponseDTO = mapper.toResponse(personEntity, null);

        assertEquals("Alguem da silva", personResponseDTO.getFullName());
        assertEquals(LocalDate.parse("1997-08-27"), personResponseDTO.getBirthDate());
        assertEquals("879.987.987-99", personResponseDTO.getDocumentNumber());
    }

    @Test
    void toResponseWithAddressAndPhoneNull() {

        PersonEntity personEntity = spy(PersonEntity.class);

        personEntity.setFullName("Alguem da silva");
        personEntity.setBirthDate(LocalDate.parse("1997-08-27"));
        personEntity.setDocumentNumber("879.987.987-99");

        PersonResponseDTO personResponseDTO = mapper.toResponse(personEntity, null);

        assertEquals("Alguem da silva", personResponseDTO.getFullName());
        assertEquals(LocalDate.parse("1997-08-27"), personResponseDTO.getBirthDate());
        assertEquals("879.987.987-99", personResponseDTO.getDocumentNumber());


    }


    @Test
    void toResponseWithNullParams() {


        PersonResponseDTO personResponseDTO = mapper.toResponse(null, null);

        assertNull(personResponseDTO);


    }
}