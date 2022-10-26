package org.example.block6pathvariableheaders;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladorUsuario {
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    //private final AtomicLong counter = new AtomicLong();

    // Recibe un usuario mediante un JSON a través de una peticion POST y lo devuelve
    @PostMapping("/user")
    public Usuario nuevoUsuario(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    // Recibe el id de un usuario y lo muestra.
    @GetMapping("/user/{id}")
    public String getUsuario(@PathVariable int id){
        for(Usuario usuario: usuarios){
            if(usuario.getId() == id){
                return usuario.toString();
            }
        }
        return "El id no coincide con ningún usuario";
    }

    // Recibe el id de un usuario y un nombre, y cambia su nombre actual por el nuevo. Lo muestra por pantalla
    @PutMapping("/post")
    public String updateUsuario(@RequestParam int id, @RequestParam String nombre){
        for(Usuario usuario: usuarios){
            if(usuario.getId() == id){
                usuario.setNombre(nombre);
                return usuario.toString();
            }
        }
        return "El id no coincide con ningún usuario";
    }
}
