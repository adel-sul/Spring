package com.cybertek.repository;

import com.cybertek.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     /*
        SEE DepartmentRepository
     */

    // TODO: Display All employees with email ...
    List<Employee> findAllByEmail(String email);

    // ----- Multiple Parameters -----
    // TODO: Display All employees with name, lastName or email ...
    List<Employee> findAllByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);

    // TODO: Display All employees with name not ...
    List<Employee> findAllByFirstNameNot(String firstName);

    // ----- Matching Conditions -----
    // TODO: Display All employees with last name starts with
    List<Employee> findAllByLastNameStartingWith(String pattern);

    // ----- Comparison Conditions -----
    // TODO: Display All employees with salaries higher than ...
    List<Employee> findAllBySalaryGreaterThan(Integer salary);

    // TODO: Display All employees with salaries less than ...
    List<Employee> findAllBySalaryLessThan(Integer salary);

    // TODO: Display All employees with hire dates between ... and ...
    List<Employee> findAllByHireDateBetween(LocalDate startDate, LocalDate endDate);

    // TODO: Display All employees with salaries >= ... in order
    List<Employee> findAllBySalaryGreaterThanEqualOrderBySalary(Integer salary);

    // ----- Limiting Query Results -----
    // TODO: Display Top 3 employees that are making less than ...
    List<Employee> findTop3BySalaryLessThan(Integer salary);

    // TODO: List employees that don't have an email
    List<Employee> findAllByEmailIsNull();
}
