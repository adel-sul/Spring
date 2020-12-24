package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long genre_id;

    private String name;

    @ManyToMany(mappedBy = "genres", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Movie> movies = new HashSet<>();

}
