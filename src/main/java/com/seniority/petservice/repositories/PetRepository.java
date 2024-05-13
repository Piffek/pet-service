package com.seniority.petservice.repositories;

import com.seniority.petservice.models.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {

    @Query("SELECT p FROM PET p where p.shelterId = :shelterId")
    List<Pet> findByShelterId(@Param("shelterId") Long shelterId);
}
