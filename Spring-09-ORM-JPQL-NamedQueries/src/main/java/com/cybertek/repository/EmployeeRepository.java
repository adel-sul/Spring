package com.cybertek.repository;

import com.cybertek.entity.Department;
import com.cybertek.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.email = 'jhookd@booking.com'")
    Employee getEmployeeDetails();

    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'jhookd@booking.com'")
    Integer getEmployeeSalary();

    // single bind parameter
    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Employee> getEmployeeByEmail(String email);

    //multiple bind parameter
    @Query("SELECT e FROM Employee e WHERE e.email = ?1 AND e.salary = ?2")
    Optional<Employee> getEmployeeByEmailAndSalary(String email, Integer salary);

    //single named parameter
    @Query("SELECT e FROM Employee e WHERE e.salary >= :salary")
    List<Employee> getEmployeesBySalary(@Param("salary") Integer salary);

    //multiple named parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName OR e.salary >= :salary")
    List<Employee> getEmployeesByFirstNameOrSalary(@Param("firstName") String firstName, @Param("salary") Integer salary);

    // Not Equal
    @Query("SELECT e FROM Employee e WHERE e.salary <> :salary")
    List<Employee> getEmployeeBySalaryNotEqual(Integer salary);

    // LIKE, CONTAINS, STARTS WITH, ENDS WITH
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE ?1")
    List<Employee> getEmployeeByFirstNameLike(String pattern);

    // BETWEEN
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :salary1 AND :salary2")
    List<Employee> getEmployeeBySalaryBetween(@Param("salary1") Integer salary1, @Param("salary2") Integer salary2);

    // BEFORE
    @Query("SELECT e FROM Employee e WHERE e.hireDate > ?1")
    List<Employee> getEmployeesByHireDateBefore(LocalDate hireDate);

    // NULL / NOT NULL
    @Query("SELECT e FROM Employee e WHERE e.email IS NULL")
    List<Employee> getEmployeesByEmailNull();
    @Query("SELECT e FROM Employee e WHERE e.email IS NOT NULL")
    List<Employee> getEmployeesByEmailNotNull();

    // Sort salary in ascending/descending order
    @Query("SELECT e FROM Employee e ORDER BY e.salary desc")
    List<Employee> getEmployeeSalaryOrderByDesc();

    // ***** NATIVE QUERIES *****
    @Query(value = "SELECT * FROM employees WHERE salary = ?1", nativeQuery = true)
    List<Employee> getEmployeeBySalary(Integer salary);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.email = 'abc@jpql.com' WHERE e.id = :id")
    void updateEmployeeJPQL(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET email = 'abc@native_query.com' WHERE id = :id", nativeQuery = true)
    void updateEmployeeNativeQuery(@Param("id") Long id);

    // named query (inside META-INF > jpa-named-queries.properties file)
    List<Department> retrieveEmployeeSalaryGreaterThan(Integer salary);


}
