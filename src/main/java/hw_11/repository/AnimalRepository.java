package hw_11.repository;



import hw_11.exception.MinCostCalculationException;
import ru.mts.animalstarter.hw_9.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {

    Map<String, LocalDate> findLeapYearNames();

    Map<Animal, Integer> findOlderAnimal(int n);

    Map<String, List<Animal>> findDuplicate();

    void printDuplicate();

    void findAverageAge(List<Animal> animals);

    List<Animal> findOldAndExpensive(List<Animal> animals);

    List<String> findMinCostAnimals(List<Animal> animals)  throws MinCostCalculationException;


}
