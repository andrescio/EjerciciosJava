package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Clase que muestra por pantalla las propiedades de application.yml
@Component
public class ApplicationYML implements CommandLineRunner {

    @Value("${greetingYML}")
    private String greeting;

    @Value("${my.numberYML}")
    private String number;

    @Value("${new.propertyYML:new.propertyYML no tiene valor}")
    private String newProperty;

    // Ejecuta estos outputs al ser llamado por el SpringApplication.run
    // de la clase pincipal (Block5PropertiesApplication)
    @Override
    public void run(String... args) throws Exception {
        System.out.println("APPLICATION.YML:");
        System.out.println("El valor de greetingYML es: "+greeting);
        System.out.println("El valor de my.numberYML es: "+number);
        System.out.println("El valor de new.propertyYML es: "+newProperty);
    }
}
