package com.person.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class PersonRequestDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    private String documentNumber;

    private LocalDate birthDate;

    @NotBlank
    private String fathersName;

    @NotBlank
    private String mothersName;

    @NotBlank
    private String typePerson;

    @Valid
    private PhoneRequestDTO phone;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(String typePerson) {
        this.typePerson = typePerson;
    }

    public PhoneRequestDTO getPhone() {
        return phone;
    }

    public void setPhone(PhoneRequestDTO phone) {
        this.phone = phone;
    }
}
