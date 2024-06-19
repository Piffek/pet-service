package com.seniority.petservice.messagebroker.receivers;

import com.google.gson.Gson;
import com.seniority.petservice.cqrs.handler.AddPetHandler;
import com.seniority.petservice.messagebroker.command.AddPetCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class PetReceiver {

    private final AddPetHandler addPetHandler;

    @RabbitListener(queues = "${pet.queue.name}")
    public void receiveMessagePet(String addPetJson) {
        var command = convertFromJson(addPetJson);
        addPetHandler.add(command);
    }

    private static AddPetCommand convertFromJson(String addPetJson) {
        var gson = new Gson();
        return gson.fromJson(addPetJson, AddPetCommand.class);
    }
}
