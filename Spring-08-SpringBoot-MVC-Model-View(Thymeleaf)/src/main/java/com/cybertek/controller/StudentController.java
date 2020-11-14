package com.cybertek.controller;

import com.cybertek.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/welcome")
    public String homePage(Model model){

        model.addAttribute("name", "Maryna");

        Random ran = new Random();
        int studentId = ran.nextInt(100)+1;
        model.addAttribute("id", studentId);

        List<Integer> list = new ArrayList<>();
        list.add(ran.nextInt(100)+1);
        list.add(ran.nextInt(100)+1);
        list.add(ran.nextInt(100)+1);
        model.addAttribute("list", list);

        LocalDate birthday = LocalDate.now().minusYears(34);
        model.addAttribute("birthday", birthday);

        Student student1 = new Student(1, "Adel", "Suleymanov");
        model.addAttribute("student", student1);

        return "student/welcome";
    }

    @GetMapping("/register")
    public String homePage2() {
        return "student/register";
    }
}
