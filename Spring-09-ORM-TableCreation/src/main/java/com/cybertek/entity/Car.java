package com.cybertek.entity;

import javax.persistence.*;

@Entity
@Table(name = "cars") // used to give table a custom name
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerate ids
    private int id;
    private String make;
    private String model;

}
