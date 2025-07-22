package com.example.user;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserKafkaConsumer.class);

    @KafkaListener(topics = "user-topic", groupId = "user-service-group")
    public void consume(String message) {
        logger.info("🟢 Received message from Kafka: {}", message);
    }
}
