package com.person.api.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_PERSON", schema = "DBPERSON")
public class PersonEntity extends SimpleEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSON")
    private Long id;

    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;

    @Column(name = "DOCUMENT_NUMBER", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "DT_BIRTH", nullable = false)
    private LocalDate birthDate;

    @Column(name = "NM_FATHER", length = 100)
    private String fathersName;

    @Column(name = "NM_MOTHER", nullable = false, length = 100)
    private String mothersName;

    @Column(name = "TP_PERSON", nullable = false, length = 20)
    private String typePerson;

    public Long getId() {
        return id;
    }

    public void setId(Long idPerson) {
        this.id = idPerson;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNUmber) {
        this.documentNumber = documentNUmber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
