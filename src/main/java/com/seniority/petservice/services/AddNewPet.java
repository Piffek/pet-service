package com.seniority.petservice.services;

import com.seniority.petservice.config.Cache;
import com.seniority.petservice.messagebroker.command.AddPetCommand;
import com.seniority.petservice.models.Pet;
import com.seniority.petservice.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewPet {

    private final PetRepository petRepository;

    @CacheEvict(value = Cache.PETS, key = "#addPetCommand.shelterId")
    public void create(AddPetCommand addPetCommand) {
        var pet = convert(addPetCommand);
        petRepository.save(pet);
    }

    private Pet convert(AddPetCommand addPetCommand) {
        return new Pet(addPetCommand.getShelterId(), addPetCommand.getName(), addPetCommand.getFoundCity());
    }
}
