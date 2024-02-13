package HW_7.scheduler;

import HW_7.repository.AnimalRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Arrays;

@Component
@EnableScheduling
public class Scheduler {
    private final AnimalRepositoryImpl animalRepository;

    public Scheduler(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Value("${sharkNames}")
    String[] sharkNames;


    @Scheduled(fixedRate = 60000)
    public void printEverySecond() {
        System.out.println("Duplicates:");
        animalRepository.printDuplicate();
        System.out.println("Leap year animals:");
        animalRepository.findLeapYearNames().forEach(System.out::println);
        System.out.println("Animals older then 10:");
        animalRepository.findOlderAnimal(10).forEach(System.out::println);
    }
}
