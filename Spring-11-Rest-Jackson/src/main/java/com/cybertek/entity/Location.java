package com.cybertek.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernate_Lazy_Initializer"}, ignoreUnknown = true)
public class Location {

    public Location(String name, BigDecimal latitude, BigDecimal longitude, String postalCode, String country, String state, String city, String address) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.postalCode = postalCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long locationId;

    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String postalCode;
    private String country;
    private String state;
    private String city;
    private String address;

//    unidirectional relationship created between tables in MovieCinema table
//    @OneToMany(mappedBy = "location", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//    private List<Cinema> cinemas;

}
