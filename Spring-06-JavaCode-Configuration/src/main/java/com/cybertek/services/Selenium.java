package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSessions;
import org.springframework.stereotype.Component;

public class Selenium implements Course {

    ExtraSessions extraSessions;

    public Selenium(ExtraSessions extraSessions) {
        this.extraSessions = extraSessions;
    }

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours : " + (17 + extraSessions.getHours()));
    }
}
