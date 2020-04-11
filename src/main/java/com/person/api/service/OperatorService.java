package com.person.api.service;

import com.person.api.dto.OperatorRequestDTO;
import com.person.api.exception.MessageErrorImpl;
import com.person.api.mapper.OperatorMapper;
import com.person.api.model.OperatorEntity;
import com.person.api.repository.OperatorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.person.api.util.MessagesUtil.getMessage;

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

    public OperatorRequestDTO getOperator(String login) {

        return mapper.toResponse(repository.findOperatorName(login).orElseThrow(() -> new EntityNotFoundException(getMessage(MessageErrorImpl.RESOURCE_NOT_FOUND, getMessage(MessageErrorImpl.OPERATOR)))));
    }

    public void remove(String login) {

        repository.deleteByLogin(login);
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
