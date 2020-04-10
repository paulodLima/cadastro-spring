package com.person.api.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
public class SimpleEntity {

    @Column(name = "DT_REGISTER", nullable = false)
    private LocalDateTime inclusionDate;

    public LocalDateTime getInclusionDate() {
        return inclusionDate;
    }

    public void setInclusionDate(LocalDateTime inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    @PrePersist
    public void setupDefaultProperties() {
        this.inclusionDate = LocalDateTime.now();

    }
}
