package com.seniority.petservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "shelter-service", fallback = ShelterServiceFallback.class)
public interface ShelterServiceClient {

    @GetMapping(value = "/api/place/find/name/{shelterId}")
    String findName(@PathVariable("shelterId") Long shelterId);
}
