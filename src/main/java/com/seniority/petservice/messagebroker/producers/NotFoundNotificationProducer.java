package com.seniority.petservice.messagebroker.producers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotFoundNotificationProducer {

    @Value("${notification.exchange.name}")
    private String topicExchangeName;

    @Value("${notification.routing.key}")
    private String notificationRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void produce(String service) {
        log.info("produce not found notification...");
        rabbitTemplate.convertAndSend(topicExchangeName, notificationRoutingKey, "%s not available".formatted(service));
    }

}
