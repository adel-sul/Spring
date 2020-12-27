package com.cybertek.repository;

import com.cybertek.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Display All employees with email ...
    List<Employee> findAllByEmail(String email);

    //Display All employees with name, lastName or email ...
    List<Employee> findAllByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);

    //Display All employees with name not ...
    List<Employee> findAllByFirstNameNot(String firstName);

    //Display All employees with last name starts with
    List<Employee> findAllByLastNameStartingWith(String pattern);

    //Display All employees with salaries higher than ...
    List<Employee> findAllBySalaryGreaterThan(Integer salary);

    //Display All employees with salaries less than ...
    List<Employee> findAllBySalaryLessThan(Integer salary);

    //Display All employees with hire dates between ... and ...
    List<Employee> findAllByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //Display All employees with salaries >= ... in order
    List<Employee> findAllBySalaryGreaterThanEqualOrderBySalary(Integer salary);

    //Display Top 3 employees that are making less than ...
    List<Employee> findTop3BySalaryLessThan(Integer salary);

    //List employees that dont have an eamil
    List<Employee> findAllByEmailIsNull();
}
