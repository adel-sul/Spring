package com.cybertek.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Tag {

    public Tag(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
        @ManyToMany mapping requires the developer to choose an owner of the relationship and a mappedBy side (Tag).
        Only one side can be the owner and the changes are propagated to the database from this side.
        CascadeType.ALL and CascadeType.REMOVE need to be avoided. Use CascadeType.PERSIST and cascadeType.MERGE
    */
    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Post> posts = new HashSet<>();
}
