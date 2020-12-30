package com.cybertek.repository;

import com.cybertek.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    /*
        Define the query methods in a repository interface('DepartmentRepository') that extends one of the Spring Data’s repositories('JpaRepository')
        Spring Data JPA will create queries automatically by parsing these method names.
        Naming convention must be followed
        Introducer clause like FIND, READ, QUERY, COUNT, or GET which tells JPA what you want to do with the method.
        This clause can contain further expressions, such as DISTINCT to set a distinct flag on the query to be created.
        Criteria clause that starts after the first BY keyword which acts as a delimiter to indicate the start of the actual query criteria.
        The criteria clause is where you define conditions on entity properties and concatenate them with AND, and OR keywords.
     */

    // TODO: Display All departments in the Furniture Department
    List<Department> findAllByDepartment(String department);

    // TODO: Display All departments in the Health Division
    List<Department> findAllByDivision(String division);
    List<Department> findByDivisionIs(String division);

    // ----- Matching Conditions -----
    // TODO: Display All departments with Division name ends with 'ics'
    List<Department> findAllByDivisionEndingWith(String pattern);

    // ----- Limiting Query Results -----
    // TODO: Display Top 3 departments with Division name includes 'Hea', without duplicates
    List<Department> findTop3DistinctByDivisionContaining(String pattern);

    /*
        Delete Queries Examples:
        void deleteByFirstName(String firstName);
        void deleteAllByAge(int age);
     */

}
