package com.person.api.mapper;

import com.person.api.dto.OperatorRequestDTO;
import com.person.api.model.OperatorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OperatorMapper {

    public abstract OperatorEntity toOperatorEntity(OperatorRequestDTO operatorRequestDTO);

    public abstract OperatorRequestDTO toResponse(OperatorEntity operatorEntity);
}
