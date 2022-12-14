package com.example.block7crudvalidation.Security;

import com.example.block7crudvalidation.Persona.Infraestructure.Repository.PersonaRepository;
import com.example.block7crudvalidation.Persona.Model.Persona;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SecurityController {

    @Autowired
    PersonaRepository personaRepository;

    @PostMapping("/login")
    public String login(@RequestParam String usuario,
                        @RequestParam String password)
    {
        List<Persona> persona = personaRepository.findByUsuario(usuario);
        if(persona.size() == 0)
            return "Usuario no encontrado";
        if(!persona.get(0).getPassword().equals(password))
            return "Contraseña incorrecta";
        if(persona.get(0).isAdmin())
            return getJwtToken(usuario, "ADMIN");
        else
            return getJwtToken(usuario, "USER");
    }

    private String getJwtToken(String username, String role) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
        String token = Jwts.builder().setId("Bosonit").setSubject(username)
                .claim("roles",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, "secret".getBytes()).compact();
        return "Bearer " + token;
    }
}
