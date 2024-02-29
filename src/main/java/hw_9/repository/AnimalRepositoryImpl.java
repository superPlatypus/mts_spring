package hw_9.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {
    List<Animal> animals = new ArrayList<>();

    @Autowired
    CreateAnimalService animalService;

    @PostConstruct
    private void postConstruct(){
        Map<String,List<Animal>> animalsMap = animalService.createAnimals();
        for (Map.Entry<String,List<Animal>> entry : animalsMap.entrySet()) {
            animals.addAll(entry.getValue());
        }

    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String,LocalDate> map = new HashMap<>();
        for (Animal animal: animals) {
            if (animal.getBirthDay().isLeapYear()){
                map.put(animal.getClass().getSimpleName() + " " + animal.getName(), animal.getBirthDay());
            }
        }
        return map;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal( int n) {
        Map<Animal, Integer> map = new HashMap<>();
        for (Animal animal: animals) {
            Integer age = Period.between(animal.getBirthDay(), LocalDate.now()).getYears();
            if (age > n){
                map.put(animal, age);
            }
        }
        if (map.isEmpty()){
            Animal oldestAnimal = animals.get(0);
            for (Animal animal: animals){
                if (oldestAnimal.getBirthDay().isAfter(animal.getBirthDay())){
                    oldestAnimal = animal;
                }
            }
            map.put(oldestAnimal, Period.between(oldestAnimal.getBirthDay(), LocalDate.now()).getYears());
        }
        return map;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        Map<Animal, Integer> animalCountMap = new HashMap<>();
        Map<String, Integer> typeCountMap = new HashMap<>();

        for (Animal animal : animals) {
            animalCountMap.put(animal, animalCountMap.getOrDefault(animal, 0) + 1);
        }
        for (Map.Entry<Animal, Integer> entry : animalCountMap.entrySet()) {
            Animal animal = entry.getKey();
            int count = entry.getValue();
            if (count > 1){
                typeCountMap.put(animal.getClass().getSimpleName(), typeCountMap.getOrDefault(animal.getClass().getSimpleName(), 0) + count);
            }
        }

        return typeCountMap;

    }

    @Override
    public void printDuplicate() {
        for (Map.Entry<String, Integer> entry : findDuplicate().entrySet()) {
            String animalType = entry.getKey();
            int count = entry.getValue();

            if (count > 1) {
                System.out.println("Animal Type: " + animalType + "\nCount: " + count);
            }

        }
    }
}
