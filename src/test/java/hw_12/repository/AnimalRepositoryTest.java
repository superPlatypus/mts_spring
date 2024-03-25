package hw_12.repository;

import hw_12.exception.AverageAgeCalculationException;
import hw_12.exception.MinCostCalculationException;
import hw_12.exception.OldAndExpensiveCalculationException;
import hw_12.exception.OlderAnimalCalculationException;
import hw_12.repository.AnimalRepositoryImpl;
import hw_12.config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.entity.Cat;
import ru.mts.animalstarter.hw_9.entity.Dog;
import ru.mts.animalstarter.hw_9.entity.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Map.entry;

@SpringBootTest
@Import(TestConfig.class)
public class AnimalRepositoryTest {
    @Autowired
    AnimalRepositoryImpl animalRepository;

    @Test
    @DisplayName("find duplicates test")
    void findDuplicateTest() {
        ConcurrentMap<String, List<Animal>> expected = new ConcurrentHashMap<>(
                Map.ofEntries(
                        entry("testCatBreed", List.of(new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()))))
        );
        Assertions.assertEquals(expected, animalRepository.findDuplicate());
    }


    @Test
    @DisplayName("negative find duplicates test")
    void negativeFindDuplicateTest() {
        ConcurrentMap<String, List<Animal>> expected = new ConcurrentHashMap<>(
                Map.ofEntries());
        Assertions.assertNotEquals(expected, animalRepository.findDuplicate());
    }

    @Test
    @DisplayName("find leap years names test")
    void findLeapYearNamesTest() {
        ConcurrentMap<String, LocalDate> expected = new ConcurrentHashMap<>(
                Map.ofEntries(
                        entry("testCat", LocalDate.now()))
        );
        Assertions.assertEquals(expected, animalRepository.findLeapYearNames());
    }


    @Test
    @DisplayName("find older then 10 years animals test")
    void findOlderAnimals() {
        ConcurrentMap<Animal, Integer> expected = new ConcurrentHashMap<>(
                Map.ofEntries(
                        entry(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1, 1)), Period.between(LocalDate.of(2001, 1, 1), LocalDate.now()).getYears()))
        );
        Assertions.assertEquals(expected, animalRepository.findOlderAnimal(10));
    }

    @Test
    @DisplayName("find older then 10 years animals test (no such animals)")
    void findOlderAnimalsNoSuchAnimals() {
        ConcurrentMap<Animal, Integer> expected = new ConcurrentHashMap<>(
                Map.ofEntries(
                        entry(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1, 1)), Period.between(LocalDate.of(2001, 1, 1), LocalDate.now()).getYears())
        ));
        Assertions.assertEquals(expected, animalRepository.findOlderAnimal(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("find old and expensive test")
    void findOldAndExpensiveTest() {
        CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>(
                List.of(
                        new Cat("testCat", BigDecimal.valueOf(1), "testCatBreed", LocalDate.now()),
                        new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()),
                        new Dog("testDog", BigDecimal.valueOf(11), "testDogBreed", LocalDate.of(2001, 1, 1)),
                        new Wolf("testWolf", BigDecimal.valueOf(11), "testWolfBreed", LocalDate.of(1990, 1, 1)))
        );
        CopyOnWriteArrayList<Animal> expected = new CopyOnWriteArrayList<>(
                List.of(
                        new Wolf("testWolf", BigDecimal.valueOf(11), "testWolfBreed", LocalDate.of(1990, 1, 1)),
                        new Dog("testDog", BigDecimal.valueOf(11), "testDogBreed", LocalDate.of(2001, 1, 1))
                )
        );
        Assertions.assertEquals(expected, animalRepository.findOldAndExpensive(animals));
    }

    @Test
    @DisplayName("find min cost animals test")
    void findMinCostAnimalsTest() throws MinCostCalculationException {
        CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>(
                List.of(
                        new Cat("testCat", BigDecimal.valueOf(1), "testCatBreed", LocalDate.now()),
                        new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()),
                        new Dog("testDog", BigDecimal.valueOf(11), "testDogBreed", LocalDate.of(2001, 1, 1)),
                        new Wolf("testWolf", BigDecimal.valueOf(12), "testWolfBreed", LocalDate.of(1990, 1, 1)))
        );
      CopyOnWriteArrayList<String> expected = new CopyOnWriteArrayList<>(
                List.of("testDog", "testCat", "testCat")
      );
      Assertions.assertEquals(expected, animalRepository.findMinCostAnimals(animals));
    }

    @Test
    @DisplayName("find average age with illegal argument")
    void findAverageAgeWithIllegalArgumentTest() {
        CopyOnWriteArrayList<Animal> animals = null;
        Assertions.assertThrows(AverageAgeCalculationException.class, () -> animalRepository.findAverageAge(animals));
    }

    @Test
    @DisplayName("find old and expensive with illegal argument")
    void findOldAndExpensiveWithIllegalArgumentTest() {
        CopyOnWriteArrayList<Animal> animals = null;
        Assertions.assertThrows(OldAndExpensiveCalculationException.class, () -> animalRepository.findOldAndExpensive(animals));
    }

    @Test
    @DisplayName("find min cost animals with illegal argument")
    void findMinCostAnimalsWithIllegalArgumentTest() {
        CopyOnWriteArrayList<Animal> animals = null;
        Assertions.assertThrows(MinCostCalculationException.class, () -> animalRepository.findMinCostAnimals(animals));
    }

    @Test
    @DisplayName("find older animal with illegal argument")
    void findfindOlderAnimalWithIllegalArgumentTest() {
        CopyOnWriteArrayList<Animal> animals = null;
        Assertions.assertThrows(OlderAnimalCalculationException.class, () -> animalRepository.findOlderAnimal(-1));
    }


}
