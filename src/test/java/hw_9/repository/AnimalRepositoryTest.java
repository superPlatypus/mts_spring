package hw_9.repository;

import hw_9.repository.AnimalRepositoryImpl;
import hw_9.config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.entity.Cat;
import ru.mts.animalstarter.hw_9.entity.Dog;

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
        Map<String, Integer> expected = Map.ofEntries(
                entry("Cat", 2));
       Assertions.assertEquals(expected, animalRepository.findDuplicate());
    }


    @Test
    @DisplayName("negative find duplicates test")
    void negativeFindDuplicateTest(){
        Map<String, Integer> expected = Map.ofEntries();
        Assertions.assertNotEquals(expected, animalRepository.findDuplicate());
    }

    @Test
    @DisplayName("find leap years names test")
    void findLeapYearNamesTest(){
        Map<String, LocalDate> expected = Map.ofEntries(
                entry("Cat testCat", LocalDate.now()));
        Assertions.assertEquals(expected, animalRepository.findLeapYearNames());
    }


    @Test
    @DisplayName("find older then 10 years animals test")
    void findOlderAnimals(){
        Map<Animal, Integer> expected = Map.ofEntries(
                entry(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1,1)), Period.between(LocalDate.of(2001, 1,1), LocalDate.now()).getYears())
        );
        Assertions.assertEquals(expected, animalRepository.findOlderAnimal(10));
    }

    @Test
    @DisplayName("find older then 10 years animals test (no such animals)")
    void findOlderAnimalsNoSuchAnimals(){
        Map<Animal, Integer> expected = Map.ofEntries(
                entry(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1,1)), Period.between(LocalDate.of(2001, 1,1), LocalDate.now()).getYears())
        );
        Assertions.assertEquals(expected, animalRepository.findOlderAnimal(Integer.MAX_VALUE));
    }



}
