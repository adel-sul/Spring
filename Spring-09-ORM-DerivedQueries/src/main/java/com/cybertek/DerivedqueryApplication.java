package com.cybertek;

import com.cybertek.repository.DepartmentRepository;
import com.cybertek.repository.EmployeeRepository;
import com.cybertek.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DerivedqueryApplication {

	@Autowired
	RegionRepository regionRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DerivedqueryApplication.class, args);
	}

	@PostConstruct
	public void testRegions() {

		System.out.println("==================== REGIONS STARTS ====================");
		System.out.println("Find By Country - " + regionRepository.findAllByCountry("Canada"));
		System.out.println("Find Distinct By Country - " + regionRepository.findDistinctByCountry("Canada"));
		System.out.println("Find All that Contain United - " + regionRepository.findAllByCountryContains("United"));
		System.out.println("Find All that Contain United, Ordered - " + regionRepository.findAllByCountryContainsOrderByCountry("United"));
		System.out.println("Find Top 2 Regions in Country - " + regionRepository.findTop2ByCountry("Canada"));
		System.out.println("==================== REGIONS END ====================");
	}

	@PostConstruct
	public void testDepartments() {
		System.out.println("==================== DEPARTMENT STARTS ====================");
		System.out.println("Find By Department - " + departmentRepository.findAllByDepartment("Furniture"));
		System.out.println("Find By Division - " + departmentRepository.findAllByDivision("Health"));
		System.out.println("Find By Division - " + departmentRepository.findByDivisionIs("Health"));
		System.out.println("Find By Division, Ends With - " + departmentRepository.findAllByDivisionEndingWith("ics"));
		System.out.println("Find Top 3 Distinct By Division, Containing - " + departmentRepository.findTop3DistinctByDivisionContaining("ics"));
		System.out.println("==================== DEPARTMENT END ====================");
	}

	@PostConstruct
	public void testEmployees() {
		System.out.println("==================== EMPLOYEE STARTS ====================");
		System.out.println("Find By Email - " + employeeRepository.findAllByEmail("dtrail8@tamu.edu"));
		System.out.println("Find By First Name Is NOT - " + employeeRepository.findAllByFirstNameNot("Sydney"));
		System.out.println("Find By Last Name Starting With - " + employeeRepository.findAllByLastNameStartingWith("Ma"));
		System.out.println("Find All without Email - " + employeeRepository.findAllByEmailIsNull());
		System.out.println("==================== EMPLOYEE END ====================");

	}
}
