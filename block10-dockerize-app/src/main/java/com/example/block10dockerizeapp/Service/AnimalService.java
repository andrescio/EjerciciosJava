package com.example.block10dockerizeapp.Service;

import com.example.block10dockerizeapp.Model.Animal;

public interface AnimalService {
    Animal addAnimal(Animal animal);
    Animal updateAnimal(Animal animal) throws Exception;
    Animal getAnimal(Integer id) throws Exception;
    String deleteAnimal(Integer id) throws Exception;
}
