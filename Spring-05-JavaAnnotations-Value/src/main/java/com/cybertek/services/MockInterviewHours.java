package com.cybertek.services;

import com.cybertek.interfaces.ExtraSession;
import org.springframework.stereotype.Component;

@Component
public class MockInterviewHours implements ExtraSession {

    @Override
    public int getHours() {
        return 2;
    }
}
