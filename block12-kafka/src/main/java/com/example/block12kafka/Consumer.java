package com.example.block12kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// This class looks for messages with bosonit-node1 topic and, when one is received, prints it.
@Service
public class Consumer {
    @KafkaListener(topics = "bosonit-node1", groupId = "group")
    public void consumeMessage(String message){
        message = "Response received in node 1\n"+message;
        System.out.println(message);
    }
}
