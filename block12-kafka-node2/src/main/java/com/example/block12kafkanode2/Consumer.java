package com.example.block12kafkanode2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// This class receives messages with bosonit-node2 as topic and calls to te method sendMessage of the Producer class
// inside this application
@Service
public class Consumer {

    @Autowired
    Producer producer;

    @KafkaListener(topics = "bosonit-node2", groupId = "group")
    public void consumeMessage(String message){
        message = "Received message in node 2\n" + message;
        producer.sendMessage(message);
    }
}
