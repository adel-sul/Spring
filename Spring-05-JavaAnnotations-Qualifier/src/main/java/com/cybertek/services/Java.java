package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Java implements Course {

    // @Qualifier through a field
    @Autowired
    @Qualifier("officeHours")
    private ExtraSession extraSession;

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours : " + (26 + extraSession.getHours()));
    }
}
