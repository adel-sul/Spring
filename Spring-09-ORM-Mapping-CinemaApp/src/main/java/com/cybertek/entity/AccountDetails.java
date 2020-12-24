package com.cybertek.entity;

import com.cybertek.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long account_details_id;

    private String address;
    private int age;
    private String city;
    private String country;
    private String name;
    private String postal_code;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String state;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accountDetails")
    private UserAccount userAccount;

}
