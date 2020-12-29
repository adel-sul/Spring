package com.cybertek.repository;

import com.cybertek.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    // ***** CUSTOM JPQL QUERIES *****
    // ----- @Query -----
    @Query("SELECT d FROM Department d WHERE d.division IN ?1")
    List<Department> getDepartmentsByDivisionIn(List<String> divisions);

    // ***** NAMED QUERIES ***** (see EmployeeRepository)
    List<Department> retrieveDepartmentByDivision(String division);

    //If the named query is a native SQL query, it needs to be annotated with the @Query annotation and set nativeQuery attribute value to true.
    @Query(nativeQuery = true) // for native queries
    List<Department> retrieveDepartmentByDivisionContains(String pattern);

    // ----- Annotations in Entity class -----
    List<Department> findDepartmentByDivisionAnnotation(String division);
    List<Department> printAllDepartmentsAnnotation();


}
