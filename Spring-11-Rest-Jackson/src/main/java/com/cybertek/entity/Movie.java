package com.cybertek.entity;

import com.cybertek.enums.MovieState;
import com.cybertek.enums.MovieType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernate_Lazy_Initializer"}, ignoreUnknown = true)
public class Movie {

    public Movie(String name, LocalDate releaseDate, Integer duration, MovieType type, MovieState state, BigDecimal price) {
        this.name = name;
        this.releaseDate = releaseDate;
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
    private LocalDate releaseDate;

    private Integer duration;

    @Column(columnDefinition = "text") // no limitation on number of characters
    private String summary;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    @Enumerated(EnumType.STRING)
    private MovieState state;

    private BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_genre_rel",
                joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>(); // best practice to use Set in @ManyToMany relationship

//    unidirectional relationship created between tables in MovieCinema table
//    @OneToMany(mappedBy = "movie", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//    private List<MovieCinema> movieCinemas;

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", release_date=" + releaseDate +
                ", duration=" + duration +
                ", summary='" + summary + '\'' +
                ", type=" + type +
                ", state=" + state +
                ", price=" + price +
                '}';
    }
}
