package HW_6.bpp;

import HW_6.service.CreateAnimalService;
import HW_6.service.CreateAnimalServiceImpl;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CreateAnimalBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        if (bean instanceof CreateAnimalService){
            ((CreateAnimalServiceImpl) bean).setAnimal();
        }
        return bean;
    }
}
