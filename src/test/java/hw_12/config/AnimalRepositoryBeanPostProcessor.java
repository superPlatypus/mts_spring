package hw_12.config;

import hw_12.repository.AnimalRepositoryImpl;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.entity.Cat;
import ru.mts.animalstarter.hw_9.entity.Dog;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class AnimalRepositoryBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        if (bean instanceof AnimalRepositoryImpl){
            try {
                Field field = ((AnimalRepositoryImpl) bean).getClass().getDeclaredField("animals");
                field.setAccessible(true);
                CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>(
                        List.of(new Cat("testCat", BigDecimal.valueOf(9), "testCatBreed", LocalDate.now()),
                        new Cat("testCat", BigDecimal.valueOf(10), "testCatBreed", LocalDate.now()),
                        new Dog("testDog", BigDecimal.valueOf(11), "testDogBreed", LocalDate.of(2001, 1,1)))
                );
                field.set(bean,animals);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}