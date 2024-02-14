package ru.mts.animalstarter.hw_7.bpp;


import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.mts.animalstarter.hw_7.service.CreateAnimalService;
import ru.mts.animalstarter.hw_7.service.CreateAnimalServiceImpl;

public class CreateAnimalBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        if (bean instanceof CreateAnimalService){
            ((CreateAnimalServiceImpl) bean).setAnimal();
            System.out.println("seted animal");
        }
        return bean;
    }
}
