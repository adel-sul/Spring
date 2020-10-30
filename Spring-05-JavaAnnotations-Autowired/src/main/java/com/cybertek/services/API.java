package com.cybertek.services;

import com.cybertek.interfaces.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class API implements Course {

    private OfficeHours officeHours;

    public OfficeHours getOfficeHours() {
        return officeHours;
    }

    // Dependency injection through a Setter
    @Autowired
    public void setOfficeHours(OfficeHours officeHours) {
        this.officeHours = officeHours;
    }

    @Override
    public void getTeachingHours() {

        System.out.println("Weekly teaching hours : " + (16 + officeHours.getHours()));

    }
}
