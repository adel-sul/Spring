package com.cybertek.entity;

import lombok.*;
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

    /*
        The mappedBy attribute characterizes a bidirectional association and must be set on the parent-side.
        It signals hibernate that key for the relationship is on the other side (Employee).
    */
    @OneToOne(mappedBy = "department")
    private Employee employee;

}
