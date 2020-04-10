package com.person.api.service;

import com.person.api.dto.OperatorRequestDTO;
import com.person.api.mapper.OperatorMapper;
import com.person.api.model.OperatorEntity;
import com.person.api.repository.OperatorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperatorService {

    private final OperatorMapper mapper;
    private final OperatorRepository repository;

    public OperatorService(OperatorMapper mapper, OperatorRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public OperatorRequestDTO insertOperator(OperatorRequestDTO operatorRequestDTO) {

        encoderPassword(operatorRequestDTO);

        System.out.println(operatorRequestDTO.getPassword());
        OperatorEntity operatorEntity = repository.save(mapper.toOperatorEntity(operatorRequestDTO));


        return mapper.toResponse(operatorEntity);
    }

    public List<OperatorRequestDTO> findAll() {

        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    public OperatorRequestDTO getOperator(Long idOperator) {

        return mapper.toResponse(repository.findById(idOperator).orElse(null));
    }

    public void remove(Long idOperator) {

        repository.deleteById(idOperator);
    }

    public OperatorRequestDTO updateOperator(Long id, OperatorRequestDTO operator) {
        OperatorEntity saveOperator = repository.getOne(id);

        BeanUtils.copyProperties(operator, saveOperator, "id");

        return mapper.toResponse(repository.save(saveOperator));
    }

    public OperatorRequestDTO encoderPassword(OperatorRequestDTO operatorRequestDTO){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        operatorRequestDTO.setPassword(passwordEncoder.encode(operatorRequestDTO.getPassword()));

        return operatorRequestDTO;
    }
}
