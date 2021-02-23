package com.cybertek.controller;

import com.cybertek.entity.Address;
import com.cybertek.entity.ResponseWrapper;
import com.cybertek.entity.Teacher;
import com.cybertek.repo.AddressRepo;
import com.cybertek.repo.ParentRepo;
import com.cybertek.repo.StudentRepo;
import com.cybertek.repo.TeacherRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    private AddressRepo addressRepo;
    private TeacherRepo teacherRepo;
    private StudentRepo studentRepo;
    private ParentRepo parentRepo;

    public ApiController(AddressRepo addressRepo, TeacherRepo teacherRepo, StudentRepo studentRepo, ParentRepo parentRepo) {
        this.addressRepo = addressRepo;
        this.teacherRepo = teacherRepo;
        this.studentRepo = studentRepo;
        this.parentRepo = parentRepo;
    }

    @GetMapping("/teachers")
    public List<Teacher> readAllTeachers() {
        return teacherRepo.findAll();
    }

    @GetMapping("/addresses")
    public List<Address> readAllAddresses() {
        return addressRepo.findAll();
    }

    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable("id") Integer id, @RequestBody Address address) throws Exception {
        Optional<Address> foundAddress = addressRepo.findById(id);

        if(!foundAddress.isPresent()) { throw new Exception("Address Does Not Exist!"); }

        address.setCurrentTemperature(new Address().consumeTemperature(address.getCity()));
        address.setId(foundAddress.get().getId());

        return addressRepo.save(address);
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper> readAllStudents(){
        return ResponseEntity.ok(new ResponseWrapper("Students successfully retrieved", studentRepo.findAll()));
    }

    @GetMapping("/parents")
    public ResponseEntity<ResponseWrapper> readAllParents(){
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Parents successfully retrieved", HttpStatus.ACCEPTED.value(), parentRepo.findAll());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseWrapper);
    }
}
