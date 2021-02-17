package com.cybertek.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}, ignoreUnknown = true)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // only allow to use Setter() on a field
    private String password;
    private String username;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    private AccountDetails accountDetails;

//    unidirectional relationship created between tables in Ticket table
//    @OneToMany(mappedBy = "userAccount", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
//    private List<Ticket> tickets;

    @Override
    public String toString() {
        return "UserAccount{" +
                "userAccountId=" + userAccountId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
