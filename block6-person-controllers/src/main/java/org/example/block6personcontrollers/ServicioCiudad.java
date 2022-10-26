package org.example.block6personcontrollers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioCiudad {
    private List<Ciudad> ciudades = new ArrayList<>();

    // Método que añade una ciudad a ciudades
    @Bean
    public Ciudad addCiudad(Ciudad ciudad){
        ciudades.add(ciudad);
        return ciudad;
    }

    // Método que devuelve la lista de ciudades
    @Bean
    public List<Ciudad> getCiudades(){
        return ciudades;
    }

}
