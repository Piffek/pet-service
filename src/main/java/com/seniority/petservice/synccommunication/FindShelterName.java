package com.seniority.petservice.synccommunication;

import com.seniority.petservice.messagebroker.producers.NotFoundNotificationProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class FindShelterName {

    private static final String FIND_NAME_PATH = "%s/api/place/find/name/%s";

    private final WebClient.Builder webClientBuilder;

    @Value("${shelter.host}")
    private String shelterHost;

    private final NotFoundNotificationProducer notificationProducer;

    @CircuitBreaker(name = "shelterApp", fallbackMethod = "fallbackMethod")
    @Retry(name = "shelterApp")
    @TimeLimiter(name = "shelterApp")
    public CompletableFuture<String> find(Long shelterId) {
        return CompletableFuture.supplyAsync(() -> webClientBuilder.build().get()
                .uri(FIND_NAME_PATH.formatted(shelterHost, shelterId))
                .retrieve()
                .bodyToMono(String.class)
                .block());
    }

    public CompletableFuture<String> fallbackMethod(Long shelterId, Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> {
            notificationProducer.produce("shelter-service");
            return "not found";
        });
    }
}
