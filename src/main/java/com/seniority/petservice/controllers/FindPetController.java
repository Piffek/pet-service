package com.seniority.petservice.controllers;

import com.seniority.petservice.dtos.PetDto;
import com.seniority.petservice.services.FindPet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pet/find")
@RequiredArgsConstructor
public class FindPetController {

    private final FindPet findPet;

    @GetMapping("/{shelterId}")
    @ResponseStatus(HttpStatus.OK)
    private List<PetDto> findAll(@PathVariable("shelterId") Long shelterId) {
        return findPet.findInShelter(shelterId);
    }
}