package hw_10.repository;

import hw_10.config.TestConfig;
import hw_10.repository.AnimalRepositoryImpl;
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

import static java.util.Map.entry;

@SpringBootTest
@Import(TestConfig.class)
public class AnimalRepositoryTest {
    @Autowired
    AnimalRepositoryImpl animalRepository;

    @Test
    @DisplayName("find duplicates test")
    void findDuplicateTest() {
        Map<String, List<Animal>> expected = Map.ofEntries(
                entry("testCatBreed", List.of(new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()))));
        Assertions.assertEquals(expected, animalRepository.findDuplicate());
    }


    @Test
    @DisplayName("negative find duplicates test")
    void negativeFindDuplicateTest() {
        Map<String, Integer> expected = Map.ofEntries();
        Assertions.assertNotEquals(expected, animalRepository.findDuplicate());
    }

    @Test
    @DisplayName("find leap years names test")
    void findLeapYearNamesTest() {
        Map<String, LocalDate> expected = Map.ofEntries(
                entry("testCat", LocalDate.now()));
        Assertions.assertEquals(expected, animalRepository.findLeapYearNames());
    }


    @Test
    @DisplayName("find older then 10 years animals test")
    void findOlderAnimals() {
        Map<Animal, Integer> expected = Map.ofEntries(
                entry(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1, 1)), Period.between(LocalDate.of(2001, 1, 1), LocalDate.now()).getYears())
        );
        Assertions.assertEquals(expected, animalRepository.findOlderAnimal(10));
    }

    @Test
    @DisplayName("find older then 10 years animals test (no such animals)")
    void findOlderAnimalsNoSuchAnimals() {
        Map<Animal, Integer> expected = Map.ofEntries(
                entry(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1, 1)), Period.between(LocalDate.of(2001, 1, 1), LocalDate.now()).getYears())
        );
        Assertions.assertEquals(expected, animalRepository.findOlderAnimal(Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("find old and expensive test")
    void findOldAndExpensiveTest() {
        List<Animal> animals = List.of(
                new Cat("testCat", BigDecimal.valueOf(1), "testCatBreed", LocalDate.now()),
                new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()),
                new Dog("testDog", BigDecimal.valueOf(11), "testDogBreed", LocalDate.of(2001, 1,1)),
                new Wolf("testWolf", BigDecimal.valueOf(11), "testWolfBreed", LocalDate.of(1990, 1,1)));
        List<Animal> expected = List.of(
                new Wolf("testWolf", BigDecimal.valueOf(11), "testWolfBreed", LocalDate.of(1990, 1, 1)),
                new Dog("testDog", BigDecimal.valueOf(11), "testDogBreed", LocalDate.of(2001, 1, 1))
        );
        Assertions.assertEquals(expected, animalRepository.findOldAndExpensive(animals));
    }

    @Test
    @DisplayName("find min cost animals test")
    void findMinCostAnimalsTest(){
        List<Animal> animals = List.of(
                new Cat("testCat", BigDecimal.valueOf(1), "testCatBreed", LocalDate.now()),
                new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()),
                new Dog("testDog", BigDecimal.valueOf(11), "testDogBreed", LocalDate.of(2001, 1,1)),
                new Wolf("testWolf", BigDecimal.valueOf(12), "testWolfBreed", LocalDate.of(1990, 1,1)));
        List<String> expected = List.of("testDog", "testCat", "testCat");
        Assertions.assertEquals(expected, animalRepository.findMinCostAnimals(animals));
    }



}
