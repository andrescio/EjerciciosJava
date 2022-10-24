package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Profile implements CommandLineRunner {

    // Para hacer funcionar este ejercicio probar con los comandos -Dspring.profiles.active=pro,
    // -Dspring.profiles.active=int y -Dspring.profiles.active=local
    @Value("${spring.profiles.active}")
    private String perfil;

    @Value("${bd.url}")
    private String bd;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Perfil:"+perfil);
        System.out.println("BD:"+bd);
    }
}
