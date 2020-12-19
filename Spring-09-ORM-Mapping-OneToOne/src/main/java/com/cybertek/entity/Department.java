package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter
@Setter
public class Department extends BaseEntity{

    public Department(String division, String department) {
        this.division = division;
        this.department = department;
    }

    private String division;
    private String department;
}
