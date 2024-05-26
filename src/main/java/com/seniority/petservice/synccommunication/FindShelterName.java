package com.seniority.petservice.synccommunication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class FindShelterName {

    private static final String FIND_NAME_PATH = "%s/api/place/find/name/%s";

    private final WebClient.Builder webClientBuilder;

    @Value("${shelter.host}")
    private String shelterHost;

    public String find(Long shelterId) {
        return webClientBuilder.build().get()
                .uri(FIND_NAME_PATH.formatted(shelterHost, shelterId))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
