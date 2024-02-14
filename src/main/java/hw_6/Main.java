package hw_6;

import hw_6.config.Config;
import hw_6.repository.AnimalRepositoryImpl;
import hw_6.service.CreateAnimalService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan()
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("Два случайных животных (scope prototype):");
        CreateAnimalService animalService = applicationContext.getBean(CreateAnimalService.class);
        System.out.println(animalService.getAnimal());
        animalService = applicationContext.getBean(CreateAnimalService.class);
        System.out.println(animalService.getAnimal());

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Main.class); // сделал разные для примера
        AnimalRepositoryImpl animalRepository = annotationConfigApplicationContext.getBean(AnimalRepositoryImpl.class);
        System.out.println("Поиск дубликатов:");
        animalRepository.printDuplicate();
        System.out.println("Животные, старше 15 лет:");
        animalRepository.findOlderAnimal(15).forEach(System.out::println);
        System.out.println("имена животных, рождённых в високосные года:");
        animalRepository.findLeapYearNames().forEach(System.out::println);
   }
}
