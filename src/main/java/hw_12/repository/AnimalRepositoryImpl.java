package hw_12.repository;


import hw_12.exception.AverageAgeCalculationException;
import hw_12.exception.MinCostCalculationException;
import hw_12.exception.OldAndExpensiveCalculationException;
import hw_12.exception.OlderAnimalCalculationException;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@Repository
public class AnimalRepositoryImpl implements AnimalRepository {
    CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();

    @Autowired
    CreateAnimalService animalService;

    @Override
    public void findAverageAge(CopyOnWriteArrayList<Animal> animals){
        if (animals == null || animals.isEmpty()) {
            throw new AverageAgeCalculationException("List of animals is null or empty");
        }
        System.out.println(animals.stream()
                .map(animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears())
                .mapToInt(Integer::intValue)
                .average());
    }


    @Override
    public CopyOnWriteArrayList<Animal> findOldAndExpensive(CopyOnWriteArrayList<Animal> animals) {
        if (animals == null || animals.isEmpty()) {
            throw new OldAndExpensiveCalculationException("List of animals is null or empty");
        }

        BigDecimal sum = animals.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageCost = sum.divide(new BigDecimal(animals.size()), RoundingMode.UP);
        return animals.stream()
                .filter(animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears() > 5)
                .filter(animal -> animal.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDay))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    @Override
    public CopyOnWriteArrayList<String> findMinCostAnimals(CopyOnWriteArrayList<Animal> animals) throws MinCostCalculationException {
        if (animals == null) {
            throw new MinCostCalculationException("List is null");
        } else if (animals.size() < 3) {
            throw new MinCostCalculationException("List size is less than 3");
        }
        return animals.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName).reversed())
                .map(Animal::getName)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    @PostConstruct
    private void postConstruct() {
        Map<String, List<Animal>> animalsMap = animalService.createAnimals();
        for (Map.Entry<String, List<Animal>> entry : animalsMap.entrySet()) {
            animals.addAll(entry.getValue());
        }

    }

    @Override
    public ConcurrentMap<String, LocalDate> findLeapYearNames() {
        return animals.stream()
                .filter(animal -> animal.getBirthDay().isLeapYear())
                .collect(Collectors.toConcurrentMap(Animal::getName, Animal::getBirthDay, (oldValue, newValue) -> oldValue));
    }

    @Override
    public ConcurrentMap<Animal, Integer> findOlderAnimal(int n) {
        if (n < 0){
            throw new OlderAnimalCalculationException("Age is less then 0");
        }
        ConcurrentMap<Animal, Integer> oldestAnimalsMap = animals.stream()
                .filter(animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears() > n)
                .collect(Collectors.toConcurrentMap(animal -> animal, animal -> Period.between(animal.getBirthDay(), LocalDate.now()).getYears(),  (oldValue, newValue) -> oldValue));
        if (!oldestAnimalsMap.isEmpty()) {
            return oldestAnimalsMap;
        } else {
            Animal oldestAnimal = animals.stream()
                    .min(Comparator.comparing(Animal::getBirthDay))
                    .orElseThrow(IllegalArgumentException::new);
            return new ConcurrentHashMap<>(Map.of(oldestAnimal, Period.between(oldestAnimal.getBirthDay(), LocalDate.now()).getYears()));
        }
    }

    @Override
    public ConcurrentMap<String, List<Animal>> findDuplicate() {
        return animals.stream()
                .filter(animal -> Collections.frequency(animals, animal) > 1)
                .skip(1)
                .collect(Collectors.groupingByConcurrent(Animal::getBreed));

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
