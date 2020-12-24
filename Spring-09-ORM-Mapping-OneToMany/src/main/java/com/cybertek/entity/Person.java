package com.cybertek.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

//    CASE-1
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "person_id")
//    private List<Address> addresses;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;
}
