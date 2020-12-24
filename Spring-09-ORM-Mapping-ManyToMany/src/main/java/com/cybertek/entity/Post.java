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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "post_tag_rel",                               // renaming join table
            joinColumns = {@JoinColumn(name = "custom1")},          // renaming 1st column
            inverseJoinColumns = {@JoinColumn(name = "custom2")})   // renaming 2nd column
    private Set<Tag> tags = new HashSet<>();
}
