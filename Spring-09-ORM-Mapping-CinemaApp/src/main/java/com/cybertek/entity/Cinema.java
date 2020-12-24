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
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long cinema_id;

    private String name;
    private String sponsored_name;

    @OneToMany(mappedBy = "cinema", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<MovieCinema> movieCinemas;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private Location location;
}
