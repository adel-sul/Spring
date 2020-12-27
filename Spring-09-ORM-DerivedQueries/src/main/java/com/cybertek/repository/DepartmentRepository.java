package com.cybertek.repository;

import com.cybertek.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    //Display All departments in the Furniture Department
    List<Department> findAllByDepartment(String department);

    //Display All departments in the Health Division
    List<Department> findAllByDivision(String division);
    List<Department> findByDivisionIs(String division);

    //Display All departments with Division name ends with 'ics'
    List<Department> findAllByDivisionEndingWith(String pattern);

    //Display Top 3 departments with Division name includes 'Hea', without duplicates
    List<Department> findTop3DistinctByDivisionContaining(String pattern);


}
