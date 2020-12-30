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

    /*
        @JoinColumn used to specify the foreign key column in the owner of the relationship.
        The inverse-side of the relationship sets the @OneToOne’s mappedBy parameter to indicate that relationship is mapped by the other entity.
        There are 2 parameters:
            name : Define the name of the foreign key column
            nullable : Defines whether the foreign key column is nullable. By default, it is true
    */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id") // custom name for column
    private Department department;

    /*
        Cascading - When we perform some action on the target entity, the same action will be applied to the associated entity.
        Always Cascade from Parent-Side to Child-Side !!!
        CascadeType.PERSIST - save() or persist() operations cascade to related entities
        CascadeType.MERGE - related entities are merged when the owning entity is merged
        CascadeType.REFRESH - refresh the state of the instance from the database, overwriting changes made to the entity
        CascadeType.REMOVE - deletes all related entities when the owning entity is deleted
        CascadeType.DETACH - removes the entity from the persistent context
    */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

}
