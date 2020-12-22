package com.cybertek.entity;

import com.cybertek.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "employees")
@NoArgsConstructor
@Setter
@Getter
public class Employee extends BaseEntity{

    public Employee(String firstName, String lastName, String email, LocalDate hireDate, Gender gender, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.gender = gender;
        this.salary = salary;
    }

    @Column(name = "name")
    private String firstName;
    private String lastName;
    private String email;

    @Column(columnDefinition = "DATE")
    private LocalDate hireDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") // custom name for column
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

}
