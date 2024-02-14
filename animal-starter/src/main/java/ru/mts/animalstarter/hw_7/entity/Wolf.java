package ru.mts.animalstarter.hw_7.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf(String name, BigDecimal cost, String breed, LocalDate birthDay){
        this.name = name;
        this.cost = cost;
        this.breed = breed;
        this.birthDay = birthDay;
    }

    public Wolf(String name, BigDecimal cost, String breed, String character, LocalDate birthday){
        this.name = name;
        this.cost = cost;
        this.breed = breed;
        this.character = character;
        this.birthDay = birthday;
    }
}
