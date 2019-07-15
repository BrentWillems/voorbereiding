package com.example.exercise.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    @Id
    private int id;

    private String name;

}
