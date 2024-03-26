package hw_13.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.animalstarter.hw_9.factory.AnimalFactory;
import ru.mts.animalstarter.hw_9.service.CreateAnimalService;
import ru.mts.animalstarter.hw_9.service.CreateAnimalServiceImpl;

@Configuration
public class Config {
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory);
    }

    @Bean
    public AnimalFactory animalFactory(){
        return new AnimalFactory();
    }
}
