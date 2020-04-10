package com.person.api.mapper;

import com.person.api.dto.PersonRequestDTO;
import com.person.api.dto.PersonResponseDTO;
import com.person.api.dto.PhoneRequestDTO;
import com.person.api.model.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    public abstract PersonEntity toEntity(PersonRequestDTO personRequestDTO);

    @Mapping(source = "phoneRequestDTO", target = "phone")
    public abstract PersonResponseDTO toResponse(PersonEntity personEntity, PhoneRequestDTO phoneRequestDTO);

}
