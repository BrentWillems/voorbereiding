package com.example.exercise.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

    @Id
    private int id;

    private int currentMilage;

    private int hp;

    private String name;

    public Vehicle() {
    }

    public Vehicle(int id, int currentMilage, int hp, String name) {
        this.id = id;
        this.currentMilage = currentMilage;
        this.hp = hp;
        this.name = name;
    }

}
