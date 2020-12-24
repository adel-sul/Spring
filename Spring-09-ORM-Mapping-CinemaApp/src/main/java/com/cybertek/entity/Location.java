package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long location_id;

    private String name;
    private String address;
    private String postal_code;
    private String country;
    private String state;
    private String city;
    private String latitude;
    private String longitude;

    @OneToMany(mappedBy = "location", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Cinema> cinemas;

}
