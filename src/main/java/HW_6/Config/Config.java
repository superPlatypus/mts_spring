package HW_6.Config;

import HW_6.Bpp.CreateAnimalBeanPostProcessor;
import HW_6.Service.CreateAnimalService;
import HW_6.Service.CreateAnimalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public static CreateAnimalBeanPostProcessor addPostProcessorImpl() {
        return new CreateAnimalBeanPostProcessor();
    }
}
