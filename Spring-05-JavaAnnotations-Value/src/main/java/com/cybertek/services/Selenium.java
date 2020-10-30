package com.cybertek.services;

import com.cybertek.interfaces.Course;
import com.cybertek.interfaces.ExtraSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Selenium implements Course {

    // @Qualifier through constructor
    public Selenium(@Qualifier("mockInterviewHours") ExtraSession extraSession) {
        this.extraSession = extraSession;
    }

    private ExtraSession extraSession;

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours : " + (26 + extraSession.getHours()));
    }
}
