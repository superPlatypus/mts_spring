package hw_12.scheduler;

import hw_12.exception.AverageAgeCalculationException;
import hw_12.exception.MinCostCalculationException;
import hw_12.exception.OldAndExpensiveCalculationException;
import hw_12.exception.OlderAnimalCalculationException;
import hw_12.repository.AnimalRepositoryImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.animalstarter.hw_9.entity.Animal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@EnableScheduling
public class Scheduler {
    private final AnimalRepositoryImpl animalRepository;

    public Scheduler(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }


    @Scheduled(fixedRate = 60000)
    public void printEveryMinute() {
        CopyOnWriteArrayList<Animal> animals = null;
        try {
            System.out.println(animalRepository.findMinCostAnimals(animals));
        } catch (MinCostCalculationException e) {
            System.out.println(e);
        }

        try {
            animalRepository.findAverageAge(animals);
        } catch (AverageAgeCalculationException e) {
            System.out.println(e);
        }

        try {
            System.out.println(animalRepository.findOldAndExpensive(animals));
        } catch (OldAndExpensiveCalculationException e) {
            System.out.println(e);
        }

        try {
            System.out.println(animalRepository.findOlderAnimal(-1));
        } catch (OlderAnimalCalculationException e) {
            System.out.println(e);
        }


    }
}
