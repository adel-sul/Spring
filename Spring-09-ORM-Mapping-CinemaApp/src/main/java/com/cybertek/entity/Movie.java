package com.cybertek.entity;

import com.cybertek.enums.MovieState;
import com.cybertek.enums.MovieType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    public Movie(String name, LocalDate release_date, Integer duration, MovieType type, MovieState state, BigDecimal price) {
        this.name = name;
        this.release_date = release_date;
        this.duration = duration;
        this.type = type;
        this.state = state;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long movieId;

    private String name;

    @Column(columnDefinition = "DATE")
    private LocalDate release_date;

    private Integer duration;

    @Column(columnDefinition = "text") // no limitation on number of characters
    private String summary;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    @Enumerated(EnumType.STRING)
    private MovieState state;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "movie_genre_rel",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>(); // best practice to use Set in @ManyToMany relationship

//    unidirectional relationship created between tables in MovieCinema table
//    @OneToMany(mappedBy = "movie", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//    private List<MovieCinema> movieCinemas;
}
