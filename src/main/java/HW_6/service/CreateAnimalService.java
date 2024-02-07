package HW_6.service;

import HW_6.entity.Animal;
import HW_6.factory.AnimalFactory;
import HW_6.factory.AnimalType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * интерфейс для создания животных
 */
public interface CreateAnimalService {

    final static List<AnimalType> animalTypes = Arrays.asList(AnimalType.values());
    final static AnimalFactory animalFactory = new AnimalFactory();
    /**
     * Создание 10 животных
     */
    default List<Animal> createAnimals(){
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
}
