package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Java implements Course {

    @Value("${instructor}")
    private String instructorName;

    @Override
    public String toString() {
        return "Java{" +
                "instructorName='" + instructorName + '\'' +
                '}';
    }

    @Autowired
    private ExtraSessions extraSessions;

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly Teaching hours : " + (11 + extraSessions.getHours()));
    }
}
