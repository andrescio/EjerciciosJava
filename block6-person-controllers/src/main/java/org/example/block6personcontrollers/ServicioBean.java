package org.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class ServicioBean {

    // Objetos persona diferentes dependiendo del Qualifier
    @Qualifier("bean1")
    private Persona bean1;

    @Qualifier("bean2")
    private Persona bean2;

    @Qualifier("bean3")
    private Persona bean3;

    // Diferentes funciones que ponen los nombres a cada uno de los beans
    @Bean
    @Qualifier("bean1")
    public Persona setNombreBean1(){
        bean1 = new Persona("bean1",11,"Ciudad1");
        return bean1;
    }

    @Bean
    @Qualifier("bean2")
    public Persona setNombreBean2(){
        bean2 = new Persona("bean2",22,"Ciudad2");
        return bean2;
    }

    @Bean
    @Qualifier("bean3")
    public Persona setNombreBean3(){
        bean3 = new Persona("bean3",33,"Ciudad3");
        return bean3;
    }

    // Devuelve el bean pedido al controladoBean. Para ello busca en la clase la propiedad con el mismo nombre
    // que el bean pedido.
    public Persona getBean(String bean) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field propiedad = Class.forName("org.example.block6personcontrollers.ServicioBean")
                                        .getDeclaredField(bean);
        Persona resultado = (Persona) propiedad.get(this);
        return resultado;
    }
}
