package com.example.exercise.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Vehicle {

    @Id
    private int id;

    private int currentMilage;

    private int hp;

    private String name;
}
