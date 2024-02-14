package hw_6.repository;

import hw_6.entity.Animal;

import java.util.List;
import java.util.Map;

public interface AnimalRepository {

    List<String> findLeapYearNames();

    List<Animal> findOlderAnimal(int n);

    Map<Animal, Integer> findDuplicate();

    void printDuplicate();
}
