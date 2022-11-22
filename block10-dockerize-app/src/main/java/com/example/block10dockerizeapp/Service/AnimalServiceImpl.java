package com.example.block10dockerizeapp.Service;

import com.example.block10dockerizeapp.Model.Animal;
import com.example.block10dockerizeapp.Repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService{

    @Autowired
    AnimalRepository animalRepository;

    @Override
    public Animal addAnimal(Animal animal) {
        animal = animalRepository.save(animal);
        return animal;
    }

    @Override
    public Animal updateAnimal(Animal animal) throws Exception {
        Optional<Animal> animalExistente = animalRepository.findById(animal.getId());
        if(animalExistente.isEmpty()){
            throw new Exception("Animal no existente");
        }
        return animalRepository.save(animal);
    }

    @Override
    public Animal getAnimal(Integer id) throws Exception{
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isEmpty()){
            throw new Exception("Animal no existente");
        }
        return animal.get();
    }

    @Override
    public String deleteAnimal(Integer id) throws Exception {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isEmpty()){
            throw new Exception("Animal no existente");
        }
        animalRepository.delete(animal.get());
        return "Registro eliminado";
    }
}
