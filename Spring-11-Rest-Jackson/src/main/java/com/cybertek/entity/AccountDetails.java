package com.cybertek.entity;

import com.cybertek.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"state", "postalCode"}, ignoreUnknown = true)
public class AccountDetails {

    public AccountDetails(String name, String address, String country, String state, String city, Integer age, String postalCode, Role role) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.age = age;
        this.postalCode = postalCode;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountDetailsId;

    // @JsonIgnore - ignore specific field
    private String name;
    private String address;
    private String country;
    private String state;
    private String city;
    private Integer age;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "accountDetails")
    @JsonBackReference
    private UserAccount userAccount;

}
