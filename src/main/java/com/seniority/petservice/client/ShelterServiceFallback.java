package com.seniority.petservice.client;

import com.seniority.petservice.messagebroker.producers.NotFoundNotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShelterServiceFallback implements ShelterServiceClient {

    private final NotFoundNotificationProducer notificationProducer;

    @Override
    public String findName(Long shelterId) {
        notificationProducer.produce("shelter-service");
        return "not found2";
    }
}
