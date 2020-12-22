package com.cybertek.bootstrap;

import com.cybertek.entity.Department;
import com.cybertek.entity.Employee;
import com.cybertek.entity.Region;
import com.cybertek.enums.Gender;
import com.cybertek.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataGenerator implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Department> departmentList = new ArrayList<>();

        Employee emp1 = new Employee("Berrie", "Manueau", "bmanueau0@dion.ne.jp", LocalDate.of(2006, 04, 28), Gender.FEMALE, 154864);
        Employee emp2 = new Employee("Aeriell", "McNee", "amcnee1@google.es", LocalDate.of(2009, 01, 26), Gender.FEMALE, 56752);
        Employee emp3 = new Employee("Sydney", "Symonds", "ssymonds2@hhs.gov", LocalDate.of(2010, 05, 17), Gender.MALE, 95313);
        Employee emp4 = new Employee("Avrom", "Rowantree", null, LocalDate.of(2003, 01, 14), Gender.FEMALE, 119674);
        Employee emp5 = new Employee("Feliks", "Morffew", "fmorffew4@a8.net", LocalDate.of(2019, 10, 14), Gender.MALE, 55307);

        Department d1 = new Department("Sports","Outdoors");
        Department d2 = new Department("Tools","Hardware");
        Department d3 = new Department("Clothing","Home");
        Department d4 = new Department("Phones & Tablets","Electronics");
        Department d5 = new Department("Computers","Electronics");

        Region r1 = new Region("Southwest","United States");
        Region r2 = new Region("Central","United States");
        Region r3 = new Region("Northwest","United States");
        Region r4 = new Region("Quebec'","Canada");
        Region r5 = new Region("Central","Asia");

        emp1.setDepartment(d1);
        emp2.setDepartment(d2);
        emp3.setDepartment(d3);
        emp4.setDepartment(d4);
        emp5.setDepartment(d5);

        emp1.setRegion(r5);
        emp2.setRegion(r4);
        emp3.setRegion(r3);
        emp4.setRegion(r2);
        emp5.setRegion(r1);

        departmentList.addAll(Arrays.asList(d1,d2,d3,d4,d5));
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
        employeeRepository.save(emp3);
        employeeRepository.save(emp4);
        employeeRepository.save(emp5);

    }
}
