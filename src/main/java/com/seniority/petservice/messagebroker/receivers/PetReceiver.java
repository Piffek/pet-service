package com.seniority.petservice.messagebroker.receivers;

import com.seniority.petservice.services.AddNewPet;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shelter.commands.AddPetCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class PetReceiver {

    private final AddNewPet addNewPet;

    @RabbitListener(queues = "${pet.queue.name}")
    public void receiveMessagePet(AddPetCommand addPetCommand) {
        log.info("Creating new pet with name {} ...", addPetCommand.getName());
        addNewPet.create(addPetCommand);
    }
}
