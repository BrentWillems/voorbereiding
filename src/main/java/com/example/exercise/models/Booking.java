package com.example.exercise.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class Booking {

    @Id
    private int id;

    @Column(name = "from_date")
    private Date from;

    @Column(name = "to_date")
    private Date to;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Vehicle vehicle;

}
