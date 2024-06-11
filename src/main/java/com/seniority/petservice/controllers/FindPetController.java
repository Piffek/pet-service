package com.seniority.petservice.controllers;

import com.seniority.petservice.cqrs.query.FindPetByIdQuery;
import com.seniority.petservice.cqrs.query.FindPetByShelterIdQuery;
import com.seniority.petservice.dtos.PetDto;
import com.seniority.petservice.cqrs.handler.FindPetHandler;
import com.seniority.petservice.messagebroker.producers.NotFoundNotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/pet/find")
@RequiredArgsConstructor
public class FindPetController {

    private final FindPetHandler findPetHandler;
    private final NotFoundNotificationProducer notificationProducer;

    @GetMapping("/shelterId/{shelterId}")
    @ResponseStatus(HttpStatus.OK)
    private List<PetDto> findAllByShelterId(@PathVariable("shelterId") Long shelterId) {
        return findPetHandler.find(new FindPetByShelterIdQuery(shelterId));
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PetDto findAllById(@PathVariable("id") Long id) throws ExecutionException, InterruptedException {
        return findPetHandler.find(new FindPetByIdQuery(id));
    }
}
