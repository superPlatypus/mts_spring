package hw_7.repository;

import hw_7.config.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.mts.animalstarter.hw_7.entity.Animal;
import ru.mts.animalstarter.hw_7.entity.Cat;
import ru.mts.animalstarter.hw_7.entity.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        Map<Animal, Integer> expected = Map.ofEntries(
                entry(new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()), 2),
                entry(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1,1)), 1)
        );
       Assertions.assertEquals(expected, animalRepository.findDuplicate());
    }

    @Test
    @DisplayName("negative find duplicates test")
    void negativeFindDuplicateTest(){
        Map<Animal, Integer> expected = Map.ofEntries();
        Assertions.assertNotEquals(expected, animalRepository.findDuplicate());
    }

    @Test
    @DisplayName("find leap years names test")
    void findLeapYearNamesTest(){
        List<String> expected = List.of("testCat", "testCat");
        Assertions.assertEquals(expected, animalRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("negative find leap years names test")
    void negativeFindLeapYearNamesTest(){
        List<String> expected = List.of("testCat");
        Assertions.assertNotEquals(expected, animalRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("find older then 10 years animals test")
    void findOlderAnimals(){
        List<Animal> expected = List.of(new Dog("testDog", BigDecimal.valueOf(10), "testDogBreed", LocalDate.of(2001, 1,1)));
        Assertions.assertEquals(expected, animalRepository.findOlderAnimal(10));
    }


}
