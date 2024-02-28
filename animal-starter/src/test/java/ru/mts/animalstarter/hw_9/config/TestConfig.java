package ru.mts.animalstarter.hw_9.config;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.animalstarter.hw_7.entity.Cat;
import ru.mts.animalstarter.hw_9.factory.AnimalFactory;
import ru.mts.animalstarter.hw_9.service.CreateAnimalServiceImpl;

@TestConfiguration
public class TestConfig {
    @Bean
    public AnimalFactory animalFactory() {
        return new AnimalFactory();
    }

    @Bean
    public CreateAnimalServiceImpl createAnimalService(){
        return new CreateAnimalServiceImpl(animalFactory());
    }
}
