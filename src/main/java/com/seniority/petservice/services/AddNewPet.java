package com.seniority.petservice.services;

import com.seniority.petservice.models.Pet;
import com.seniority.petservice.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.shelter.commands.AddPetCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewPet {

    private final PetRepository petRepository;

    public void create(AddPetCommand addPetCommand) {
        var pet = convert(addPetCommand);
        petRepository.save(pet);
    }

    private Pet convert(AddPetCommand addPetCommand) {
        return new Pet(addPetCommand.getDestinationShelterId(), addPetCommand.getName(), addPetCommand.getFoundCity());
    }
}
