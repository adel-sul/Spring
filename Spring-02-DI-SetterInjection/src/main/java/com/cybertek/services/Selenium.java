package com.cybertek.services;

import com.cybertek.interfaces.Course;
import lombok.Data;

public class Selenium implements Course {
    public void getTeacingHours() {
        System.out.println("Weekly teaching hours - 20");
    }
}
