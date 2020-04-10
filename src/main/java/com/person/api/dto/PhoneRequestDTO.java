package com.person.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class PhoneRequestDTO {

    @NotBlank
    @Valid
    private String number;

    private Long codArea;

    @NotBlank
    @Valid
    private String phoneType;

    @NotBlank
    private String operatorLogin;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getCodArea() {
        return codArea;
    }

    public void setCodArea(Long codArea) {
        this.codArea = codArea;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getOperatorLogin() {
        return operatorLogin;
    }

    public void setOperatorLogin(String operatorLogin) {
        this.operatorLogin = operatorLogin;
    }
}
