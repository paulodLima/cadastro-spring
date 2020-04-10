package com.person.api.controller;

import com.person.api.dto.OperatorRequestDTO;
import com.person.api.dto.PersonRequestDTO;
import com.person.api.dto.PersonResponseDTO;
import com.person.api.model.PersonEntity;
import com.person.api.service.OperatorService;
import com.person.api.service.PersonService;

import org.springframework.web.bind.annotation.*;

import javax.transaction.TransactionScoped;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    private final PersonService personService;
    private final OperatorService operatorService;


    public PersonController(PersonService personService,OperatorService operatorService ) {
        this.personService = personService;
        this.operatorService = operatorService;
    }

   @PostMapping("persons")
    public PersonResponseDTO insert(@RequestBody @Valid PersonRequestDTO personRequestDTO) {

        return personService.createPerson(personRequestDTO);
    }

    @GetMapping("persons")
    public List<PersonResponseDTO> listPerson() {
        return personService.getPersons();
    }

    @GetMapping("persons/{documentNumber}")
    public PersonResponseDTO getUser(@PathVariable String documentNumber){
        return  personService.getPerson(documentNumber);
    }

    @PutMapping("persons/{documentNumber}")
    public PersonEntity updatePerson(@PathVariable String documentNumber, @Valid @RequestBody PersonResponseDTO personResponseDTO){
        return personService.update(documentNumber,personResponseDTO);
    }
    @TransactionScoped
    @DeleteMapping("persons/{documentNumber}")
    public void delete(@PathVariable String documentNumber){
         personService.delete(documentNumber);
    }

    @PostMapping("admin/operator")
    public OperatorRequestDTO insert(@RequestBody @Valid OperatorRequestDTO operatorRequestDTO){
       return operatorService.insertOperator(operatorRequestDTO);
    }

    @DeleteMapping("admin/operator/{id}")
    public void deleteOperator(@PathVariable Long id){
        operatorService.remove(id);
    }

    @GetMapping("operator")
    public List<OperatorRequestDTO> listAllOperator(){
        return operatorService.findAll();
    }

    @GetMapping("operator/{id}")
    public OperatorRequestDTO getOperator(@PathVariable Long id){
        return operatorService.getOperator(id);
    }

    @PutMapping("admin/operator/{id}")
    public OperatorRequestDTO updateOperator(@PathVariable Long id,@Valid @RequestBody OperatorRequestDTO operatorRequestDTO){
        return operatorService.updateOperator(id,operatorRequestDTO);
    }

}

