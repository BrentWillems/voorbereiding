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

    private int currentMilage;

    private int hp;

    private String name;

    public Vehicle( int currentMilage, int hp, String name) {
        this.currentMilage = currentMilage;
        this.hp = hp;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getCurrentMilage() {
        return currentMilage;
    }

    public void setCurrentMilage(int currentMilage) {
        this.currentMilage = currentMilage;
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
