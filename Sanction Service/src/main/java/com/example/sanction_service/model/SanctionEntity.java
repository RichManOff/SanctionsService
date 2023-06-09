package com.example.sanction_service.model;

import jakarta.persistence.*;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Entity
@Table(name = "sanctions")
@XmlRootElement(name = "CONSOLIDATED_LIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class SanctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "FIRST_NAME")
    private String name;

    @XmlElement(name = "SECOND_NAME")
    private String surname;

    public SanctionEntity() {
    }

    public SanctionEntity(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
