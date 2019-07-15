package com.example.exercise.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Vehicle {

    @Id
    private int id;

    private int currentMilage;

    private int hp;

    private String name;

    public Vehicle(int id, int currentMilage, int hp, String name) {
        this.id = id;
        this.currentMilage = currentMilage;
        this.hp = hp;
        this.name = name;
    }
}
