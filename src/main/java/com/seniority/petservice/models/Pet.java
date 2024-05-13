package com.seniority.petservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "PET")
@Table(name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long shelterId;
    private String name;
    private String found;

    // for JPA only, no use
    public Pet() {
    }

    public Pet(Long shelterId, String name, String found) {
        this.shelterId = shelterId;
        this.name = name;
        this.found = found;
    }
}

