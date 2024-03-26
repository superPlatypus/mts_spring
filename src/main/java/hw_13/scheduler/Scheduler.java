package hw_13.scheduler;

import hw_13.exception.AverageAgeCalculationException;
import hw_13.exception.MinCostCalculationException;
import hw_13.exception.OldAndExpensiveCalculationException;
import hw_13.exception.OlderAnimalCalculationException;
import hw_13.repository.AnimalRepositoryImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.animalstarter.hw_9.entity.Animal;
import ru.mts.animalstarter.hw_9.entity.Cat;
import ru.mts.animalstarter.hw_9.entity.Dog;
import ru.mts.animalstarter.hw_9.entity.Wolf;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.*;

@Component
@EnableScheduling
public class Scheduler {
    private final AnimalRepositoryImpl animalRepository;

    public Scheduler(AnimalRepositoryImpl animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostConstruct
    private void postConstruct() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2, new NamedThreadFactory("ScheduledThread"));
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Duplicates");
                animalRepository.printDuplicate();
                System.out.println("---------------");
            }
        }, 0, 10, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : Average age");
                animalRepository.findAverageAge(new CopyOnWriteArrayList<>(
                        List.of(
                                new Cat("Cat1", BigDecimal.valueOf(10), "CatBreed", LocalDate.of(2004, 1, 1)),
                                new Dog("Dog1", BigDecimal.valueOf(11), "DogBreed",  LocalDate.of(2014, 1, 1)),
                                new Wolf("Wolf1", BigDecimal.valueOf(11), "WolfBreed", LocalDate.of(2024, 1, 1)))
                ));
                System.out.println("---------------");
            }
        }, 0, 20, TimeUnit.SECONDS);
    }

    static class NamedThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private int count = 0;

        public NamedThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, namePrefix + count++);
        }
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
