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
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long user_account_id;

    private String email;
    private String password;
    private String username;

    @OneToMany(mappedBy = "userAccount", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Ticket> tickets;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "")
    private AccountDetails accountDetails;
}
