package com.cybertek.repository;

import com.cybertek.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    // Get List of departments in multiple divisions
    @Query("SELECT d FROM Department d WHERE d.division IN ?1")
    List<Department> getDepartmentsByDivisionIn(List<String> divisions);

}
