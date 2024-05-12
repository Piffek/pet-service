package com.seniority.petservice.repositories;

import com.seniority.petservice.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface AddPetRepository extends CrudRepository<Pet, Long> {
}
