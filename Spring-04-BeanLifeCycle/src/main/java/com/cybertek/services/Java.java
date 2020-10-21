package com.cybertek.services;

import com.cybertek.interfaces.Course;

public class Java implements Course {

    @Override
    public void getTeacingHours() {
        System.out.println("Weekly teaching hours - 45" );
    }

    public void initMethod() {
        System.out.println("Executing Init Method");
    }

    public void destroyMethod() {
        System.out.println("Executing Destroy Method");
    }
}
