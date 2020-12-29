package com.cybertek.repository;

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

    // ***** CUSTOM JPQL QUERIES *****
    // ----- @Query -----
    @Query("SELECT e FROM Employee e WHERE e.email = 'jhookd@booking.com'")
    Employee getEmployeeDetails();

    @Query("SELECT e.salary FROM Employee e WHERE e.email = 'jhookd@booking.com'")
    Integer getEmployeeSalary();

    /*
    A positional bind parameter is referenced by its position in the query.
    They are defined with ? followed by a number that specifies the position (?1, ?2, etc.).
    */
    //single bind parameter
    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Employee> getEmployeeByEmail(String email);

    //multiple bind parameter
    @Query("SELECT e FROM Employee e WHERE e.email = ?1 AND e.salary = ?2")
    Optional<Employee> getEmployeeByEmailAndSalary(String email, Integer salary);

    /*
    Named bind parameters start with : followed by the name of the parameter.
    Can be passed in any order without worrying about their position
    @Param annotation specifies the name of the bind parameter in the method definition.
    Each method parameter annotated with @Param must have a corresponding bind parameter in the JPQL or SQL query.
    */
    //single named parameter
    @Query("SELECT e FROM Employee e WHERE e.salary >= :salary")
    List<Employee> getEmployeesBySalary(@Param("salary") Integer salary);

    //multiple named parameters
    @Query("SELECT e FROM Employee e WHERE e.firstName = :firstName OR e.salary >= :salary")
    List<Employee> getEmployeesByFirstNameOrSalary(@Param("firstName") String firstName, @Param("salary") Integer salary);

    // ---------- Practice ----------
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

    // ----- @Modifying -----
    /*
    used to to execute INSERT, UPDATE, DELETE queries.
    requires @Transactional annotation
    */
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.email = 'abc@jpql.com' WHERE e.id = :id")
    void updateEmployeeJPQL(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET email = 'abc@native_query.com' WHERE id = :id", nativeQuery = true)
    void updateEmployeeNativeQuery(@Param("id") Integer id);

    // ***** NAMED QUERIES *****
    /*
    Can be defined by by using an external properties file, Java annotations, or an XML file.
    Can be grouped in one place and referred in code by their names.
    Spring Data JPA will take care of all the boilerplate code required to execute these queries.
    To reference the named query in Spring Data JPA repository, it's name should start with the name of the entity class, followed by a dot (.), and the name of the method
    */
    // defining by using a properties file jpa-named-queries.properties inside the META-INF folder under /src/main/resources/
    List<Employee> retrieveEmployeeSalaryGreaterThan(Integer salary);


}
