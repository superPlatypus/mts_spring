package HW_6.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Shark extends Predator {
    public Shark(String name, BigDecimal cost, String breed, LocalDate birthDay){
        this.name = name;
        this.cost = cost;
        this.breed = breed;
        this.birthDay = birthDay;
    }

    public Shark(String name, BigDecimal cost, String breed, String character, LocalDate birthday){
        this.name = name;
        this.cost = cost;
        this.breed = breed;
        this.character = character;
        this.birthDay = birthday;
    }
}