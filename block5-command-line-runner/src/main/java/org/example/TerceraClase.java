package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TerceraClase implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Soy la tercera clase");
        System.out.println("args = " + Arrays.deepToString(args));
    }
}
