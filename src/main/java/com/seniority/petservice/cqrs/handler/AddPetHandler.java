package com.seniority.petservice.cqrs.handler;

import com.seniority.petservice.services.AddNewPet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shelter.commands.AddPetCommand;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class AddPetHandler {
    private final AddNewPet addNewPet;

    public void add(AddPetCommand command) {
        log.info("Creating new pet with name {} ...", command.getName());
        addNewPet.create(command);
    }
}
