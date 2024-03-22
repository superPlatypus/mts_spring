package hw_12.repository;



import hw_12.exception.MinCostCalculationException;
import ru.mts.animalstarter.hw_9.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public interface AnimalRepository {

    ConcurrentMap<String, LocalDate> findLeapYearNames();

    ConcurrentMap<Animal, Integer> findOlderAnimal(int n);

    ConcurrentMap<String, List<Animal>> findDuplicate();

    void printDuplicate();

    void findAverageAge(CopyOnWriteArrayList<Animal> animals);

    CopyOnWriteArrayList<Animal> findOldAndExpensive(CopyOnWriteArrayList<Animal> animals);

    CopyOnWriteArrayList<String> findMinCostAnimals(CopyOnWriteArrayList<Animal> animals)  throws MinCostCalculationException;


}
