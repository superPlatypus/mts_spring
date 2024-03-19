package hw_11.scheduler;

import hw_11.exception.AverageAgeCalculationException;
import hw_11.exception.MinCostCalculationException;
import hw_11.exception.OldAndExpensiveCalculationException;
import hw_11.exception.OlderAnimalCalculationException;
import hw_11.repository.AnimalRepositoryImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.animalstarter.hw_9.entity.Animal;

import java.util.List;

@Component
@EnableScheduling
public class Scheduler {
    private final AnimalRepositoryImpl animalRepository;

    public Scheduler(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }


    @Scheduled(fixedRate = 60000)
    public void printEveryMinute() {
        List<Animal> animals = null;
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
