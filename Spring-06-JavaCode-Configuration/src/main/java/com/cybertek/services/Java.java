package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSessions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
// creating a bean using @Component annotation
public class Java implements Course {


    @Value("B11")
    private String batch;

    @Value("${instructor}")
    private String instructor;

    @Override
    public String toString() {
        return "Java{" +
                "batch='" + batch + '\'' +
                ", instructor='" + instructor + '\'' +
                ", days=" + Arrays.toString(days) +
                '}';
    }

    @Value("${days}")
    private String[] days;

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours : 23");
    }
}
