package ru.mts.animalstarter.hw_9.service;



import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.factory.AnimalFactory;
import ru.mts.animalstarter.hw_9.factory.AnimalType;

import java.util.*;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    AnimalFactory animalFactory;
    public CreateAnimalServiceImpl(AnimalFactory animalFactory){
        this.animalFactory = animalFactory;
    }

    public AnimalFactory getAnimalFactory() {
        return animalFactory;
    }

    private Animal animal;

    @Override
    public void setAnimal() {
        animal = animalFactory.createAnimal();
    }

    @Override
    public Animal getAnimal() {
        return animal;
    }

    public Map<String, List<Animal>> createAnimals(int n){
        if (n < 0){
            throw new IllegalArgumentException();
        }
        List<Animal> animals = new ArrayList<>();
        int i = 0;
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        while (i < n){
            Animal animal = animalFactory.createAnimal();
            List<Animal> animalList = animalsMap.get(animal.getClass().getSimpleName());
            if (animalList == null){
                animalList = new ArrayList<>();
                animalsMap.put(animal.getClass().getSimpleName(), animalList);
            }
            animalList.add(animal);
            i++;
        }
        return animalsMap;
    }
}
