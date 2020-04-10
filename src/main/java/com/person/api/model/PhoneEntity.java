package com.person.api.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_PHONE", schema = "DBPERSON")
public class PhoneEntity extends SimpleEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHONE")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_PERSON", foreignKey = @ForeignKey(name = "FK_PERSON_PHONE"), nullable = false, unique = true)
    private PersonEntity personEntity;

    @Column(name = "NUMBER", length = 10)
    private String number;

    @Column(name = "COD_AREA", length = 3)
    private Long codArea;

    @Column(name = "PHONE_TYPE")
    private String phoneType;

    @Column(name = "OPERATOR_LOGIN")
    private String operatorLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneEntity that = (PhoneEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
