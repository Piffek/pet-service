package com.seniority.petservice.services;

import com.seniority.petservice.dtos.PetDto;
import com.seniority.petservice.models.Pet;
import com.seniority.petservice.repositories.PetRepository;
import com.seniority.petservice.synccommunication.FindShelterName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPet {

    private final PetRepository petRepository;
    private final FindShelterName findShelterName;

    public List<PetDto> findInShelter(Long shelterId) {
        var shelterName = findShelterName.find(shelterId);
        return petRepository.findByShelterId(shelterId).stream()
                .map(pet -> convert(pet, shelterName))
                .toList();
    }

    private PetDto convert(Pet pet, String shelterName) {
        return new PetDto(pet.getName(), pet.getFound(), shelterName);
    }
}
