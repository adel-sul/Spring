package com.cybertek.services;

import com.cybertek.interfaces.Course;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Selenium implements Course {

    // @Autowired not necessary. Lombok creating 1 constructor.
    private OfficeHours officeHours;

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours : " + (26 + officeHours.getHours()));
    }
}
