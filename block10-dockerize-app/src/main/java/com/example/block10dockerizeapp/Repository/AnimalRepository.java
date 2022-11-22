package com.example.block10dockerizeapp.Repository;

import com.example.block10dockerizeapp.Model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
