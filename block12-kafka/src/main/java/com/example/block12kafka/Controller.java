package com.example.block12kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    Producer producer;

    // Receives a message via Post request and calls to producer.sendMessage() method to send it. There is a Postman
    // json included inside this application to make this request. It is located just below the pom.xml.
    @PostMapping("/kafka")
    public void messageToTopic (@RequestParam("message") String message){
        producer.sendMessage(message);
    }
}
