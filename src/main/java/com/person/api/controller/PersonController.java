package com.person.api.controller;

import com.person.api.dto.OperatorRequestDTO;
import com.person.api.dto.PersonRequestDTO;
import com.person.api.dto.PersonResponseDTO;
import com.person.api.dto.PhoneRequestDTO;
import com.person.api.model.PersonEntity;
import com.person.api.service.OperatorService;
import com.person.api.service.PersonService;
import com.person.api.service.PhoneService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.TransactionScoped;
import javax.validation.Valid;
import javax.xml.stream.events.StartElement;
import java.util.List;

@RestController
@RequestMapping("/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    private final PersonService personService;
    private final OperatorService operatorService;
    private final PhoneService phoneService;


    public PersonController(PersonService personService, OperatorService operatorService, PhoneService phoneService) {
        this.personService = personService;
        this.operatorService = operatorService;
        this.phoneService = phoneService;
    }

    @GetMapping("login")
    public String login(){
        return "autentiaco com sucesso";
    }
   @PostMapping("gestor/persons")
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

    @PutMapping("gestor/persons/{documentNumber}")
    public PersonEntity updatePerson(@PathVariable String documentNumber, @Valid @RequestBody PersonResponseDTO personResponseDTO){
        return personService.update(documentNumber,personResponseDTO);
    }
    @TransactionScoped
    @DeleteMapping("gestor/persons/{documentNumber}")
    public void delete(@PathVariable String documentNumber){
         personService.delete(documentNumber);
    }

    @PostMapping("admin/operator")
    public OperatorRequestDTO insert(@RequestBody @Valid OperatorRequestDTO operatorRequestDTO){
       return operatorService.insertOperator(operatorRequestDTO);
    }

    @TransactionScoped
    @DeleteMapping("admin/operator/{id}")
    public void deleteOperator(@PathVariable String login){
        operatorService.remove(login);
    }

    @GetMapping("gestor/operator")
    public List<OperatorRequestDTO> listAllOperator(@AuthenticationPrincipal UserDetails userDetails){
        System.out.println(userDetails);
        return operatorService.findAll();
    }

    @GetMapping("gestor/operator/{id}")
    public OperatorRequestDTO getOperator(@PathVariable String id){
        return operatorService.getOperator(id);
    }

    @PutMapping("admin/operator/{id}")
    public OperatorRequestDTO updateOperator(@PathVariable Long id,@Valid @RequestBody OperatorRequestDTO operatorRequestDTO){
        return operatorService.updateOperator(id,operatorRequestDTO);
    }

    @PostMapping("/telefone")
    public PhoneRequestDTO cadastarTelefone(@RequestBody @Valid PhoneRequestDTO phoneRequestDTO, PersonEntity personEntity){
        return this.phoneService.processPhone(phoneRequestDTO,personEntity);
    }
}

