package com.cybertek;

import com.cybertek.configs.CybertekAppConfig;
import com.cybertek.interfaces.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CybertekApp {

    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(CybertekAppConfig.class);
        Course course1 = container.getBean("java", Course.class);
        course1.getTeachingHours();

        Course course2 = container.getBean("selenium", Course.class);
        course2.getTeachingHours();

        System.out.println(course1.toString());


    }
}
