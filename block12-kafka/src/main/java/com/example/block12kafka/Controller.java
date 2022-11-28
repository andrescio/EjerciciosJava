package com.example.block12kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Producer producer;

    @Autowired
    public Controller(Producer producer){
        this.producer = producer;
    }

    @PostMapping("/kafka")
    public void messageToTopic (@RequestParam("message") String message){
        producer.sendMessage(message);
    }
}
