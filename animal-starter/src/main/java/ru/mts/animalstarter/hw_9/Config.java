package ru.mts.animalstarter.hw_9;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.animalstarter.hw_9.bpp.CreateAnimalBeanPostProcessor;

@Configuration
public class Config {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessorImpl() {
        return new CreateAnimalBeanPostProcessor();
    }
}
