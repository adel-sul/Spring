package com.cybertek.services;

import com.cybertek.interfaces.Course;

public class Java implements Course {

    OfficeHours officeHours;

    public Java(OfficeHours officeHours) {
        this.officeHours = officeHours;
    }

    public void getTeacingHours() {
        System.out.println("Weekly teaching hours - " + (45+officeHours.getHours()));
    }
}
