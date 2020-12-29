package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "departments")
@ToString
/*
Named queries can be declared by annotating entities with the following annotations
Both @NamedQuery and @NamedNativeQuery annotations can be used separately or together on the entity class
Multiple JPQL named queries can be defined together using the @NamedQueries annotation
If native SQL named query returns an entity, it needs to be explicitly indicated by using the resultClass element.
 */
@NamedQuery(name = "Department.findDepartmentByDivisionAnnotation", // used to define a JPQL named query.
            query = "SELECT d FROM Department d WHERE d.division = ?1")
@NamedNativeQuery(name = "Department.printAllDepartmentsAnnotation",          // Used to define a native SQL named query
        query = "SELECT * FROM departments",
        resultClass = Department.class)
public class Department{

    @Id
    private String department;
    private String division;
}
