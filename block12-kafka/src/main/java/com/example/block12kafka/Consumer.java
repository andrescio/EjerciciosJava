package com.example.block12kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    @KafkaListener(topics = "bosonit-node1", groupId = "group")
    public void consumeMessage(String message){
        message = "Response received in node 1\n"+message;
        System.out.println(message);
    }
}
