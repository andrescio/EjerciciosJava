package org.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/controlador")
public class ControladorBean {

    @Autowired
    private ServicioBean servicioBean;

    @GetMapping(value="/bean/{bean}")
    public Persona getBean(@PathVariable String bean) throws NoSuchFieldException,
                                                             ClassNotFoundException, IllegalAccessException {
        return servicioBean.getBean(bean);
    }

}
