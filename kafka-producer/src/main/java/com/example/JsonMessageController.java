package com.example;

import com.example.payload.GeoJsonMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private KafkaProducer kafkaProducer;

    public JsonMessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody GeoJsonMessage message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("GeoJson Message sent to the topic");
    }
}
