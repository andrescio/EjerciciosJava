package com.example.block10dockerizeapp.Controller;

import com.example.block10dockerizeapp.Model.Animal;
import com.example.block10dockerizeapp.Service.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    AnimalServiceImpl animalServiceImpl;

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal){
        return animalServiceImpl.addAnimal(animal);
    }

    @PutMapping
    public Animal updateAnimal(@RequestBody Animal animal) throws Exception{
        return animalServiceImpl.updateAnimal(animal);
    }

    @GetMapping("/{id}")
    public Animal findAnimal(@PathVariable Integer id) throws Exception {
        return animalServiceImpl.getAnimal(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable Integer id) throws Exception{
        return animalServiceImpl.deleteAnimal(id);
    }
}
