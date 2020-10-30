package com.cybertek.services;

import com.cybertek.interfaces.ExtraSession;
import org.springframework.stereotype.Component;

@Component
public class OfficeHours implements ExtraSession {

    public int getHours() {
        return 4;
    }
}
