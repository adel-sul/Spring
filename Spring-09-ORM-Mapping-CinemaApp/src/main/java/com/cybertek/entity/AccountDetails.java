package com.cybertek.entity;

import com.cybertek.enums.Role;
import lombok.*;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AccountDetails {

    public AccountDetails(String address, Integer age, String city, String country, String name, String postalCode, Role role, String state) {
        this.address = address;
        this.age = age;
        this.city = city;
        this.country = country;
        this.name = name;
        this.postalCode = postalCode;
        this.role = role;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountDetailsId;

    private String address;
    private Integer age;
    private String city;
    private String country;
    private String name;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String state;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accountDetails")
    private UserAccount userAccount;

}
