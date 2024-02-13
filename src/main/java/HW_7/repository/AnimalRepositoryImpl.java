package HW_7.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.mts.animalstarter.HW_7.entity.Animal;
import ru.mts.animalstarter.HW_7.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
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
                System.out.println("Animal: " + animal + "\nCount: " + count);
            }

        }
    }
}
