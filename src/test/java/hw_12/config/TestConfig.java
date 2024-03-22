package hw_12.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public static AnimalRepositoryBeanPostProcessor addPostProcessorImpl() {
        return new AnimalRepositoryBeanPostProcessor();
    }
}
