package com.example.block12kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// This class sends a message with a topic bosonit-node2. The receiver of this message will be
// block12-kafka-node2 Consumer
@Service
public class Producer {
    private static final String topic = "bosonit-node2";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
        kafkaTemplate.send(topic, message);
    }
}
