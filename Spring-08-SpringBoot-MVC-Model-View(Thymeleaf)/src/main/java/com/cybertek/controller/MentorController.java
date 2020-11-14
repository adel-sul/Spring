package com.cybertek.controller;

import com.cybertek.enums.Gender;
import com.cybertek.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @GetMapping("/list")
    public String showTable(Model model) {

        List<Mentor> mentors = new ArrayList<>();
        mentors.add(new Mentor("Mike", "Smith", 31, Gender.MALE));
        mentors.add(new Mentor("Lisa", "Brown", 27, Gender.FEMALE));
        mentors.add(new Mentor("John", "Snow", 36, Gender.MALE));

        model.addAttribute("mentors", mentors);
        return "mentor/mentor-list";
    }
}
