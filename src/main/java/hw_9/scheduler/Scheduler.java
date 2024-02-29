package hw_9.scheduler;

import hw_9.repository.AnimalRepositoryImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Scheduler {
    private final AnimalRepositoryImpl animalRepository;

    public Scheduler(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }


    @Scheduled(fixedRate = 60000)
    public void printEveryMinute() {
        System.out.println("Duplicates:");
        System.out.println(animalRepository.findDuplicate());
        System.out.println("Leap year animals:");
        System.out.println(animalRepository.findLeapYearNames());
        System.out.println("Animals older then 10:");
        System.out.println(animalRepository.findOlderAnimal(10));
        System.out.println("--------------");
    }
}
