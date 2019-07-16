package com.example.exercise.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue
    private int id;

    private int currentMileage;

    private int hp;

    private String name;

    public Vehicle(int currentMileage, int hp, String name) {
        this.currentMileage = currentMileage;
        this.hp = hp;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
