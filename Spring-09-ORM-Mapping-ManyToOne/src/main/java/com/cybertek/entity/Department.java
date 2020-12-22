package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter
@Setter
public class Department extends BaseEntity{

    public Department(String department, String division) {
        this.department = department;
        this.division = division;
    }

    private String department;
    private String division;


}
