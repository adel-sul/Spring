package com.cybertek.bootstrap;

import com.cybertek.entity.Department;
import com.cybertek.entity.Employee;
import com.cybertek.enums.Gender;
import com.cybertek.repository.DepartmentRepository;
import com.cybertek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) throws Exception {
        Employee emp1 = new Employee("Berrie", "Manueau", "bmanueau0@dion.ne.jp", LocalDate.of(2006, 04, 28), Gender.FEMALE, 154864);
        Employee emp2 = new Employee("Aeriell", "McNee", "amcnee1@google.es", LocalDate.of(2009, 01, 26), Gender.FEMALE, 56752);
        Employee emp3 = new Employee("Sydney", "Symonds", "ssymonds2@hhs.gov", LocalDate.of(2010, 05, 17), Gender.MALE, 95313);
        Employee emp4 = new Employee("Avrom", "Rowantree", null, LocalDate.of(2003, 01, 14), Gender.FEMALE, 119674);
        Employee emp5 = new Employee("Feliks", "Morffew", "fmorffew4@a8.net", LocalDate.of(2019, 10, 14), Gender.MALE, 55307);

        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);
        employeeRepository.save(emp5);

        Department dep1 = new Department("Home", "Clothing");
        Department dep2 = new Department("Home", "Grocery");
        Department dep3 = new Department("Home", "Decor");
        Department dep4 = new Department("Electronics", "Device Repair");
        Department dep5 = new Department("Electronics", "Phones & Tablets");

        departmentRepository.save(dep1);
        departmentRepository.save(dep2);
        departmentRepository.save(dep3);
        departmentRepository.save(dep4);
        departmentRepository.save(dep5);
    }
}
