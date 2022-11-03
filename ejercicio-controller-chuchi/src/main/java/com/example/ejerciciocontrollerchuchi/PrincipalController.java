package com.example.ejerciciocontrollerchuchi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class PrincipalController {
    ControllerObject controllerObject = new ControllerObject();

    // Controlador por defecto
    @GetMapping(value = "{configKey}/**")
    public ControllerObject entryOther(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        // Redirige a /salta si hay un header REDIRIGE con el valor SALTA
        if(request.getHeader("REDIRIGE").equals("SALTA")){
            response.sendRedirect("/salta");
        }

        // Añade los path al objeto ControllerObject
        List<String> paths = Arrays.stream(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)
                                            .toString()
                                            .split("/"))
                                            .toList();
        controllerObject.setPaths(paths);

        // Añade los parámetros al objeto ControllerObject
        Map<String,String[]> queries = request.getParameterMap();
        controllerObject.setQueries(queries);

        // Añade los headers al objeto ControllerObject
        Map<String,String> headers = new HashMap<String,String>();
        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers.put(key, value);
        }
        controllerObject.setHeaders(headers);

        // Añade la IP al objeto ControllerObject
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        controllerObject.setUrlOrigen(ip);

        return controllerObject;
    }

    @GetMapping(value={"/","one"})
    public String entryOne(final HttpServletRequest request) {
        return "Ha accedido a entryOne";
    }

    @GetMapping(value={"/salta"}) public String entryJump( ) {
        return "he ido a Jump";
    }
}
