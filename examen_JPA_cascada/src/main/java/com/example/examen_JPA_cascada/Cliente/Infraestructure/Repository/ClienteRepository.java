package com.example.examen_JPA_cascada.Cliente.Infraestructure.Repository;

import com.example.examen_JPA_cascada.Cliente.Model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
