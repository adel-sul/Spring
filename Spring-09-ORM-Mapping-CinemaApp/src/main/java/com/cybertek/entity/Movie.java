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

    public Movie(Integer duration, String name, BigDecimal price, LocalDate release_date, MovieState state, String summary, MovieType type) {
        this.duration = duration;
        this.name = name;
        this.price = price;
        this.release_date = release_date;
        this.state = state;
        this.summary = summary;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long movieId;

    private Integer duration;
    private String name;
    private BigDecimal price;

    @Column(columnDefinition = "DATE")
    private LocalDate release_date;

    @Enumerated(EnumType.STRING)
    private MovieState state;

    @Column(columnDefinition = "text") // no limitation on number of characters
    private String summary;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "movie_genre_rel",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>(); // best practice to use Set in @ManyToMany relationship

//    unidirectional relationship created between tables in MovieCinema table
//    @OneToMany(mappedBy = "movie", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//    private List<MovieCinema> movieCinemas;
}
