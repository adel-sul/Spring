package com.cybertek.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Location {

    public Location(String name, BigDecimal latitude, BigDecimal longitude, String address, String postalCode, String country, String state, String city) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long locationId;

    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private String postalCode;
    private String country;
    private String state;
    private String city;

//    unidirectional relationship created between tables in MovieCinema table
//    @OneToMany(mappedBy = "location", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//    private List<Cinema> cinemas;

}
