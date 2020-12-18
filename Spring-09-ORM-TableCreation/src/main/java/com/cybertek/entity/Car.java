package com.cybertek.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "cars") // used to give table a custom name
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autogenerate ids
    private int id;
    private String make;
    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }
}
