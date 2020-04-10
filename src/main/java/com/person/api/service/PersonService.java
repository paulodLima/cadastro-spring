package com.person.api.service;

import com.person.api.dto.PersonRequestDTO;
import com.person.api.dto.PersonResponseDTO;
import com.person.api.exception.MessageErrorImpl;
import com.person.api.mapper.PersonMapper;
import com.person.api.model.PersonEntity;
import com.person.api.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.person.api.util.MessagesUtil.getMessage;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;
    private final PhoneService phoneService;

    public PersonService(PersonRepository personRepository, PersonMapper mapper,PhoneService phoneService) {
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.phoneService = phoneService;
    }


    public PersonResponseDTO createPerson(PersonRequestDTO personRequestDTO) {

        PersonEntity personEntity = personRepository.save(mapper.toEntity(personRequestDTO));

        return mapper.toResponse(personEntity, phoneService.processPhone(personRequestDTO.getPhone(), personEntity));

    }

    public PersonResponseDTO getPerson(String documentNumber) {

        PersonEntity personEntity = personRepository.findByCpf(documentNumber).orElseThrow(() -> new EntityNotFoundException(getMessage(MessageErrorImpl.RESOURCE_NOT_FOUND, getMessage(MessageErrorImpl.PERSON))));

        return mapper.toResponse(personEntity, phoneService.getPhone(personEntity.getId()));
    }

    public List<PersonResponseDTO> getPersons() {

        return personRepository.findAll().stream().map(x -> mapper.toResponse(x,phoneService.getPhone(x.getId()))).collect(Collectors.toList());
    }


    public PersonEntity update(String documentNumber, PersonResponseDTO personResponseDTO) {

        PersonEntity savePerson = personRepository.findByCpf(documentNumber).orElseThrow(() -> new EntityNotFoundException(getMessage(MessageErrorImpl.RESOURCE_NOT_FOUND, getMessage(MessageErrorImpl.PERSON))));

        BeanUtils.copyProperties(personResponseDTO, savePerson, "id");

        return personRepository.save(savePerson);
    }

    public void delete(String documentNumber){
       personRepository.deleteByCpf(documentNumber);
    }
}
