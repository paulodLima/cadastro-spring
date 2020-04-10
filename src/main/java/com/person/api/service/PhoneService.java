package com.person.api.service;

import com.person.api.dto.PhoneRequestDTO;
import com.person.api.mapper.PhoneMapper;
import com.person.api.model.PersonEntity;
import com.person.api.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PhoneService {


    private final PhoneMapper mapper;

    private final PhoneRepository phoneRepository;


    public PhoneService(PhoneMapper mapper, PhoneRepository telephoneRepository) {
        this.mapper = mapper;
        this.phoneRepository = telephoneRepository;
    }

    public List<PhoneRequestDTO> findAll (){

        return phoneRepository.findAll().stream().map(mapper::toPhoneDTO).collect(Collectors.toList());
    }

    public PhoneRequestDTO getPhone(@PathVariable Long idPhone){

        return mapper.toPhoneDTO(phoneRepository.findById(idPhone).orElse(null));

    }

    public PhoneRequestDTO processPhone(@Valid @RequestBody PhoneRequestDTO telephoneDTO, PersonEntity personEntity){
        if (Objects.isNull(telephoneDTO)) {
            return null;
        }
        return mapper.toPhoneDTO(phoneRepository.save(mapper.toPhoneEntity(telephoneDTO,personEntity)));
    }

    public void deleteTelephone( @PathVariable Long id) {

        phoneRepository.deleteById(id);
    }
}
