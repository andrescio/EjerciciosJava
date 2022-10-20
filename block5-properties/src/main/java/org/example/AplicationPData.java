package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Clase que muestra por pantalla las propiedades de application.properties
@Component
public class AplicationPData implements CommandLineRunner {

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private String number;

    // new.property no existe en application.properties.
    @Value("${new.property:new.property no tiene valor}")
    private String newProperty;

    // Ejecuta estos outputs al ser llamado por el SpringApplication.run
    // de la clase pincipal (Block5PropertiesApplication)
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\nAPPLICATION.PROPERTIES:");
        System.out.println("El valor de greeting es: "+greeting);
        System.out.println("El valor de my.number es: "+number);
        System.out.println("El valor de new.property es: "+newProperty);
        System.out.println("-----------------------------------------");
    }
}
