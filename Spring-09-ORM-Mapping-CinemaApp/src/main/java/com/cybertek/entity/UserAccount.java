package com.cybertek.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {

    public UserAccount(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userAccountId;

    private String email;
    private String password;
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    private AccountDetails accountDetails;

//    unidirectional relationship created between tables in Ticket table
//    @OneToMany(mappedBy = "userAccount", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//    private List<Ticket> tickets;

}
