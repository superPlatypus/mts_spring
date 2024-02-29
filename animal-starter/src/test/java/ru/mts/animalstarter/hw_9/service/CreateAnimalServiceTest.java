package ru.mts.animalstarter.hw_9.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.animalstarter.hw_9.config.TestConfig;

@SpringBootTest
@Import(TestConfig.class)
@ActiveProfiles("test")
public class CreateAnimalServiceTest {

    @Autowired
    CreateAnimalServiceImpl createAnimalService;

    @Test
    @DisplayName("Create animals test")
    void createAnimalsTest(){
        Assertions.assertNotNull(createAnimalService.createAnimals());
    }

    @Test
    @DisplayName("Create animals test with param")
    void createAnimalsTestWithParam(){
        Assertions.assertNotNull(createAnimalService.createAnimals(15));
    }

    @Test
    @DisplayName("create animals with illegal argument test")
    void createAnimalsWithIllegalArg(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> createAnimalService.createAnimals(-1));
    }

}
