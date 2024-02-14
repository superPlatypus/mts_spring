package ru.mts.animalstarter.hw_7.service;



import org.springframework.stereotype.Service;
import ru.mts.animalstarter.hw_7.entity.Animal;
import ru.mts.animalstarter.hw_7.factory.AnimalFactory;
import ru.mts.animalstarter.hw_7.factory.AnimalType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * интерфейс для создания животных
 */
@Service
public interface CreateAnimalService {

    final static List<AnimalType> animalTypes = Arrays.asList(AnimalType.values());
//    final static AnimalFactory animalFactory = new AnimalFactory();


    /**
     * Создание 10 животных
     */
    default List<Animal> createAnimals(){
        AnimalFactory animalFactory = getAnimalFactory();
        List<Animal> animals = new ArrayList<>();
        int i = 0;
        Random random = new Random();
        while (i < 10){
            animals.add(animalFactory.createAnimal());
            i++;
        }
        return animals;
    }

    void setAnimal();
    Animal getAnimal();

    AnimalFactory getAnimalFactory();
}
