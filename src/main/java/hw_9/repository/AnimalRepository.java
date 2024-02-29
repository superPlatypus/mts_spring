package hw_9.repository;



import ru.mts.animalstarter.hw_9.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {

    Map<String, LocalDate> findLeapYearNames();

    Map<Animal, Integer> findOlderAnimal(int n);

    Map<String, Integer> findDuplicate();

    void printDuplicate();
}
