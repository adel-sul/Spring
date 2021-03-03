package com.cybertek.entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Genre {

    public Genre(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long genreId;

    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    private List<Movie> movies = new ArrayList<>();

}
