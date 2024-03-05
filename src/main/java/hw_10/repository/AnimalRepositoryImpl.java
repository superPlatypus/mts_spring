package hw_10.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {
    List<Animal> animals = new ArrayList<>();

    @Autowired
    CreateAnimalService animalService;

    @Override
    public void findAverageAge(List<Animal> animals) {
        System.out.println(animals.stream()
                .map(animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears())
                .mapToInt(Integer::intValue)
                .average().orElseThrow(IllegalArgumentException::new));
    }


    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {

        BigDecimal sum = animals.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageCost = sum.divide(new BigDecimal(animals.size()), RoundingMode.UP);
        return animals.stream()
                .filter(animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears() > 5)
                .filter(animal -> animal.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDay))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinCostAnimals(List<Animal> animals) {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .map(Animal::getName)
                .collect(Collectors.toList());
    }

    @PostConstruct
    private void postConstruct() {
        Map<String, List<Animal>> animalsMap = animalService.createAnimals();
        for (Map.Entry<String, List<Animal>> entry : animalsMap.entrySet()) {
            animals.addAll(entry.getValue());
        }

    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        return animals.stream()
                .filter(animal -> animal.getBirthDay().isLeapYear())
                .collect(Collectors.toMap(Animal::getName, Animal::getBirthDay, (oldValue, newValue) -> oldValue));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        Map<Animal, Integer> oldestAnimalsMap = animals.stream()
                .filter(animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears() > n)
                .collect(Collectors.toMap(animal -> animal, animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears(),  (oldValue, newValue) -> oldValue));
        if (!oldestAnimalsMap.isEmpty()) {
            return oldestAnimalsMap;
        } else {
            Animal oldestAnimal = animals.stream()
                    .min(Comparator.comparing(Animal::getBirthDay))
                    .orElseThrow(IllegalArgumentException::new);
            return Map.of(oldestAnimal, Period.between(oldestAnimal.getBirthDay(), LocalDate.now()).getYears());
        }
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        return animals.stream()
                .filter(animal -> Collections.frequency(animals, animal) > 1)
                .skip(1)
                .collect(Collectors.groupingBy(Animal::getBreed));

    }

    @Override
    public void printDuplicate() {
        for (Map.Entry<String, List<Animal>> entry : findDuplicate().entrySet()) {
            String animalType = entry.getKey();
            List<Animal> animals = entry.getValue();
            System.out.println("Duplicates of " + animalType);
            animals.forEach(System.out::println);
        }
    }
}
