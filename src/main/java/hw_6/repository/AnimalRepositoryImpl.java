package hw_6.repository;

import hw_6.entity.Animal;
import hw_6.service.CreateAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnimalRepositoryImpl implements AnimalRepository {
    List<Animal> animals;

    @Autowired
    CreateAnimalService animalService;

    @PostConstruct
    private void postConstruct(){
        animals = animalService.createAnimals();
    }

    @Override
    public List<String> findLeapYearNames() {
        return animals.stream()
                .filter(animal -> animal.getBirthDay().isLeapYear())
                .map(Animal::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> findOlderAnimal( int n) {
        return animals.stream().
                filter(animal -> animal.getBirthDay().until(LocalDate.now(), ChronoUnit.YEARS) > n)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Animal, Integer> findDuplicate() {
        Map<Animal, Integer> animalCountMap = new HashMap<>();


        for (Animal animal : animals) {
            animalCountMap.put(animal, animalCountMap.getOrDefault(animal, 0) + 1);
        }

        return animalCountMap;

    }

    @Override
    public void printDuplicate() {
        for (Map.Entry<Animal, Integer> entry : findDuplicate().entrySet()) {
            Animal animal = entry.getKey();
            int count = entry.getValue();

            if (count > 1) {
                System.out.println("Животное " + animal + " встречается " + count + " раз(а).");
            }

        }
    }
}
