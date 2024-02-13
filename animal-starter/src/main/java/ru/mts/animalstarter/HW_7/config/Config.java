package ru.mts.animalstarter.HW_7.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.animalstarter.HW_7.bpp.CreateAnimalBeanPostProcessor;
import ru.mts.animalstarter.HW_7.service.CreateAnimalService;
import ru.mts.animalstarter.HW_7.service.CreateAnimalServiceImpl;

@Configuration
public class Config {
    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessorImpl() {
        return new CreateAnimalBeanPostProcessor();
    }
}
