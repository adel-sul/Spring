package com.cybertek.service;

import com.cybertek.entity.Student;
import com.cybertek.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentData() {
        return studentRepository.findAll();
    }
}
