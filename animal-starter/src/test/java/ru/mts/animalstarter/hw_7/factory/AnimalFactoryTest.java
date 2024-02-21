package ru.mts.animalstarter.hw_7.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.animalstarter.hw_7.config.TestConfig;

import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@Import(TestConfig.class)
@ActiveProfiles("test")
public class AnimalFactoryTest {
    @Autowired
    AnimalFactory animalFactory;

    @Test
    @DisplayName("create animal test")
    void createAnimalTest(){
        Assertions.assertNotNull(animalFactory.createAnimal());
        Mockito.verify(animalFactory, times(1 + 1)).createAnimal(); // + 1 , так как метод вызовается еще один раз в BeanPostProcessor
    }
}
