package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long movie_id;

    private int duration;
    private String name;
    private double price;
    private String release_date;
    private String state;
    private String summary;
    private String type;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "movie_genre_rel",
                joinColumns = {@JoinColumn(name = "movie_id")},
                inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<MovieCinema> movieCinemas;
}
