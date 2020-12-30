package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
@NoArgsConstructor
@Getter
@Setter
public class Region extends BaseEntity{

    public Region(String region, String country) {
        this.region = region;
        this.country = country;
    }

    private String region;
    private String country;

    /*
        The mappedBy attribute characterizes a bidirectional association and must be set on the parent-side.
        It signals hibernate that key for the relationship is on the other side (Employee).
    */
    @OneToOne(mappedBy = "region")
    private Employee employee;
}
