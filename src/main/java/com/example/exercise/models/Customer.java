package com.example.exercise.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    private int id;

    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
