package com.person.api.mapper;

import com.person.api.dto.PhoneRequestDTO;
import com.person.api.model.PersonEntity;
import com.person.api.model.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class  PhoneMapper {

    @Mapping(source = "personEntity", target = "personEntity")
    @Mapping(target = "id", ignore = true)
    public abstract PhoneEntity toPhoneEntity (PhoneRequestDTO phoneRequestDTO, PersonEntity personEntity);

    public  abstract PhoneRequestDTO toPhoneDTO (PhoneEntity telephoneEntity);
}
