package com.cybertek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getRequestMapping() {
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adel")
    public String getRequestMapping2() {
        return "home";
    }

    // same as one above, using different annotation
    @GetMapping("/adel2")
    public String getRequestMapping3() {
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/maryna")
    public String getRequestMapping4() {
        return "home";
    }

    // same as one above, using different annotation
    @PostMapping("/maryna2")
    public String getRequestMapping5() {
        return "home";
    }

    @GetMapping("/home/{name}")
    public String pathVarExample(@PathVariable("name") String path) {
        return "home";
    }

    // 2 path params
    @GetMapping("/home/{name}/{email}")
    public String pathVarExample(@PathVariable("name") String path, @PathVariable("email") String path2) {
        return "home";
    }

    // query param
    @GetMapping("/home/course")
    public String queryParamExample(@RequestParam("course") String course) {
        System.out.println("Course is : " + course);
        return "home";
    }

    @GetMapping(value = "/course")
    public String requestParam(@RequestParam(value = "course", required = false, defaultValue = "cybertek") String name) {
        System.out.println("name is : " + name);
        return "home";

    }
}
