package com.example.block12kafkanode2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// For this program to work is needed to run this application and block12-kafka application at the same time
// in different ports. A Kafka Broker is needed to be running too.
@SpringBootApplication
public class Block12KafkaNode2Application {

	public static void main(String[] args) {
		SpringApplication.run(Block12KafkaNode2Application.class, args);
	}

}
