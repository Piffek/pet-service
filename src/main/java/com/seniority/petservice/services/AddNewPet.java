package com.seniority.petservice.services;

import com.seniority.petservice.models.Pet;
import com.seniority.petservice.repositories.AddPetRepository;
import lombok.RequiredArgsConstructor;
import org.shelter.commands.AddPetCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddNewPet {

    private final AddPetRepository addPetRepository;

    public void create(AddPetCommand addPetCommand) {
        var pet = convert(addPetCommand);
        addPetRepository.save(pet);
    }

    private Pet convert(AddPetCommand addPetCommand) {
        return new Pet(addPetCommand.getDestinationShelterId(), addPetCommand.getName(), addPetCommand.getFoundCity());
    }
}
