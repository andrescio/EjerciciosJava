package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TerceraClase implements CommandLineRunner {
    @Bean
    CommandLineRunner ejecutaTerceraClase()
    {
        return p ->
        {
            System.out.println("Soy la tercera clase");
        };
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
