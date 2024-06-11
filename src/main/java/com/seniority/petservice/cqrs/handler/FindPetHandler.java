package com.seniority.petservice.cqrs.handler;

import com.seniority.petservice.cqrs.query.FindPetByIdQuery;
import com.seniority.petservice.cqrs.query.FindPetByShelterIdQuery;
import com.seniority.petservice.dtos.PetDto;
import com.seniority.petservice.services.FindPet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class FindPetHandler {
    private final FindPet findPet;

    public List<PetDto> find(FindPetByShelterIdQuery query) {
        return findPet.byShelterId(query.shelterId());
    }

    public PetDto find(FindPetByIdQuery query) throws ExecutionException, InterruptedException {
        return findPet.byId(query.id());
    }
}
