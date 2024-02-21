package ru.mts.animalstarter.hw_7.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.animalstarter.hw_7.config.TestConfig;
import ru.mts.animalstarter.hw_7.entity.Animal;
import ru.mts.animalstarter.hw_7.entity.Cat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@Import(TestConfig.class)
@ActiveProfiles("test")
public class CreateAnimalServiceTest {
    @Autowired
    CreateAnimalServiceImpl createAnimalService;

    @Value("${name}")
    String name;

    @Test
    @DisplayName("default create animals test")
    void createAnimalTest(){
        Assertions.assertNotNull(createAnimalService.createAnimals());
    }

    @Test
    @DisplayName("create animals with param test")
    void createAnimalWithParamTest(){
        List<Animal> expected = Stream.generate(() -> new Cat(name, BigDecimal.valueOf(1), "SimpleCatBreed", LocalDate.now()))
                .limit(15)
                .collect(Collectors.toList());
        Assertions.assertEquals(expected, createAnimalService.createAnimals(15));
        Mockito.verify(createAnimalService.getAnimalFactory(), times(15 + 1)).createAnimal(); // + 1 , так как метод вызовается еще один раз в BeanPostProcessor
    }

    @Test
    @DisplayName("create animals with illegal argument test")
    void createAnimalsWithIllegalArg(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalService.createAnimals(-1));
    }
}
