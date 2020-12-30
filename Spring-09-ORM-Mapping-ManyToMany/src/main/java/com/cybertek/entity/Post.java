package com.cybertek.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    /*
        The bidirectional @ManyToMany association can be navigated from both sides, therefore, both sides can be parents(parent-side).
        None of them will hold a foreign key. In this association, there are two foreign keys that are stored in a separate table, known as the junction or join table (post_tag_rel).
        @JoinTable provides a mapping between two tables.
    */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "post_tag_rel",                               // renaming join table
            joinColumns = {@JoinColumn(name = "custom1")},          // renaming 1st column
            inverseJoinColumns = {@JoinColumn(name = "custom2")})   // renaming 2nd column
    private Set<Tag> tags = new HashSet<>();
}
