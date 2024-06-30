package com.seniority.petservice.services;

import com.seniority.petservice.config.Cache;
import com.seniority.petservice.dtos.PetDto;
import com.seniority.petservice.models.Pet;
import com.seniority.petservice.repositories.PetRepository;
import com.seniority.petservice.synccommunication.FindShelterName;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class FindPet {

    private final PetRepository petRepository;
    private final FindShelterName findShelterName;

    @Cacheable(Cache.PETS)
    public List<PetDto> byShelterId(Long shelterId) {
        var shelterName = findShelterName.find(shelterId);
        return petRepository.findByShelterId(shelterId).stream()
                .map(pet -> {
                    try {
                        return convert(pet, shelterName.get());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    public PetDto byId(Long id) throws ExecutionException, InterruptedException {
        var pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("cannot find pet with id %s".formatted(id)));
        var shelterName = findShelterName.find(pet.getShelterId());
        return convert(pet, shelterName.get());
    }

    private PetDto convert(Pet pet, String shelterName) {
        return new PetDto(pet.getName(), pet.getFound(), shelterName);
    }
}
