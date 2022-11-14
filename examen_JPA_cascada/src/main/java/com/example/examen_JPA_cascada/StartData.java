package com.example.examen_JPA_cascada;

import com.example.examen_JPA_cascada.CabeceraFra.Service.FacturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// Clase con un método que se ejecuta una vez iniciada la aplicación.
@Component
public class StartData {
    @Autowired
    FacturaServiceImpl facturaServiceImpl;

    // Método que llama al método insercionInicial de facturaServiceImpl. Crea el Cliente, y la CabeceraFra con
    // las LineasFra
    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        facturaServiceImpl.insercionInicial();
    }
}
