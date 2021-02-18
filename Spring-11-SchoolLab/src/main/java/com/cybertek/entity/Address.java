package com.cybertek.entity;

import com.cybertek.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "teacher"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address extends BaseEntity{

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Student student;

    //ignored on field level
    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Parent parent;

    //ignored on class level
    @OneToOne(mappedBy = "address")
    private Teacher teacher;

    private Integer currentTemperature;

    private Integer getCurrentTemperature() {
        return consumeTemperature(this.city);
    };

    public Integer consumeTemperature(String city) {
        return 5;
    }

}
