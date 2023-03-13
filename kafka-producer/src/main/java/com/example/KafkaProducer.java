package com.example;

import com.example.payload.GeoJsonMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, GeoJsonMessage> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, GeoJsonMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(GeoJsonMessage data) {
        LOGGER.info(String.format("GeoJson Message sent %s", data.toString()));

        Message<GeoJsonMessage> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "GeoJson8")
                .build();

        kafkaTemplate.send(message);
    }
}
