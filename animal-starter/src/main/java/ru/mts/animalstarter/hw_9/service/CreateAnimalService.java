package ru.mts.animalstarter.hw_9.service;



import org.springframework.stereotype.Service;
import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.factory.AnimalFactory;
import ru.mts.animalstarter.hw_9.factory.AnimalType;

import java.util.*;

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
    default Map<String, List<Animal>> createAnimals(){
        AnimalFactory animalFactory = getAnimalFactory();
        List<Animal> animals = new ArrayList<>();
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        int i = 0;
        while (i < 10){
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

    void setAnimal();
    Animal getAnimal();

    AnimalFactory getAnimalFactory();
}
