package ru.mts.animalstarter.hw_7.service;



import ru.mts.animalstarter.hw_7.entity.Animal;
import ru.mts.animalstarter.hw_7.factory.AnimalFactory;
import ru.mts.animalstarter.hw_7.factory.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<Animal> createAnimals(int n){
        if (n < 0){
            throw new IllegalArgumentException();
        }
        List<Animal> animals = new ArrayList<>();
        int i = 0;
        Random random = new Random();
        while (i < n){
            AnimalType animalType = animalTypes.get(random.nextInt(4));
            animals.add(animalFactory.createAnimal());
            i++;
        }
        return animals;
    }
}
