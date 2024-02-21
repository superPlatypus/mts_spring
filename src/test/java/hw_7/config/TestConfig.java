package hw_7.config;

import hw_7.repository.AnimalRepository;
import hw_7.repository.AnimalRepositoryImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.animalstarter.hw_7.bpp.CreateAnimalBeanPostProcessor;
import ru.mts.animalstarter.hw_7.entity.Animal;
import ru.mts.animalstarter.hw_7.entity.Cat;
import ru.mts.animalstarter.hw_7.entity.Dog;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@TestConfiguration
public class TestConfig {
    @Bean
    public static AnimalRepositoryBeanPostProcessor addPostProcessorImpl() {
        return new AnimalRepositoryBeanPostProcessor();
    }
}
