package com.cybertek.services;

import com.cybertek.interfaces.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SQL implements Course {

    // Field injection
    @Autowired
    private OfficeHours officeHours;

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours : " + (6 + officeHours.getHours()));
    }
}
