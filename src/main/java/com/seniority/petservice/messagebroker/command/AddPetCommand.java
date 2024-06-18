package com.seniority.petservice.messagebroker.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class AddPetCommand implements Serializable {
    private final String name;
    private final String foundCity;
    private final Long shelterId;
}
