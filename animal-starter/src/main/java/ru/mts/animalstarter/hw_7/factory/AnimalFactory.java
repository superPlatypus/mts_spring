package ru.mts.animalstarter.hw_7.factory;

import org.springframework.beans.factory.annotation.Value;
import ru.mts.animalstarter.hw_7.entity.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AnimalFactory {
    final private static List<String> catBreeds = new ArrayList<>(Arrays.asList("Bengal", "British Shorthair", "Sphynx"));
    final private static List<String> dogBreeds = new ArrayList<>(Arrays.asList("Labrador", "German Shepherd", "Poodle"));
    final private static List<String> wolfBreeds = new ArrayList<>(Arrays.asList("Gray Wolf", "Red Wolf"));
    final private static List<String> sharkBreeds = new ArrayList<>(Arrays.asList("Whale Shark", "Tiger Shark"));
//    @Value("#{'${cat.names}'.split(',')}")
    @Value("${catNames}")
    String[] catNames;
    @Value("${dogNames}")
    String[] dogNames;
    @Value("${wolfNames}")
    String[] wolfNames;
    @Value("${sharkNames}")
     String[] sharkNames;


    public Animal createAnimal(){
        Animal animal = null;
        Random random = new Random();

        switch (Arrays.asList(AnimalType.values()).get(random.nextInt(4))){
            case CAT:
                animal = new Cat(
                        catNames[random.nextInt(catNames.length)],
                        BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP),
                        catBreeds.get(random.nextInt(3)),
                        getRandomDate());
                break;
            case DOG:
                animal = new Dog(
                        dogNames[random.nextInt(dogNames.length)],
                        BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP),
                        dogBreeds.get(random.nextInt(3)),
                        getRandomDate());
                break;
            case WOLF:
                animal = new Wolf(
                        wolfNames[random.nextInt(wolfNames.length)],
                        BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP),
                        wolfBreeds.get(random.nextInt(2)),
                        getRandomDate());
                break;
            case SHARK:
                animal = new Shark(
                        sharkNames[random.nextInt(sharkNames.length)],
                        BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, RoundingMode.HALF_UP),
                        sharkBreeds.get(random.nextInt(2)),
                        getRandomDate());
                break;
        }

        return animal;
    }

    public static LocalDate getRandomDate() {
        Random random = new Random();
        int year = random.nextInt(24) + 2000;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1; //
        return LocalDate.of(year, month, day);
    }
}
