package ru.mts.animalstarter.hw_7.config;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.animalstarter.hw_7.entity.Cat;
import ru.mts.animalstarter.hw_7.factory.AnimalFactory;
import ru.mts.animalstarter.hw_7.service.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

@TestConfiguration
public class TestConfig {
    @Value("${name}")
    String name;
    @Bean
    public AnimalFactory animalFactory() {
        AnimalFactory factory = Mockito.mock(AnimalFactory.class);
        Mockito.when(factory.createAnimal()).thenReturn(new Cat(name, BigDecimal.valueOf(1), "SimpleCatBreed", LocalDate.now()));

        return factory;
    }

    @Bean
    public CreateAnimalServiceImpl createAnimalService(){
        return new CreateAnimalServiceImpl(animalFactory());
    }
}
