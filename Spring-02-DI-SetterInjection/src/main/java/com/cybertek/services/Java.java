package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSessions;
import lombok.Data;

@Data
public class Java implements Course {

    private OfficeHours officeHours;

    public void getTeacingHours() {
        System.out.println("Weekly teaching hours - " + (45+officeHours.getHours()));
    }
}
