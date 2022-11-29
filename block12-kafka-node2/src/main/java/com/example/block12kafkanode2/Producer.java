package com.example.block12kafkanode2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// This class is instanced when this application´s Consumer receives a message. This class sends a message
// with a bosonit-node1 topic and the receiver will be block12-kafka Consumer
@Service
public class Producer {
    private static final String topic = "bosonit-node1";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
        kafkaTemplate.send(topic, message);
    }
}
