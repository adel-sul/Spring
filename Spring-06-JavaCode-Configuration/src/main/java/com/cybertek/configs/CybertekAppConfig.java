package com.cybertek.configs;

import com.cybertek.services.OfficeHours;
import com.cybertek.services.Selenium;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.cybertek")
@PropertySource("classpath:application.properties")
public class CybertekAppConfig {

    @Bean
    // creating a bean using @Bean annotation
    // used when we don't have access to actual class (read only)
    // dependency injection with @Bean
    public Selenium selenium() {
        return new Selenium(officeHours());
    }

    @Bean
    public OfficeHours officeHours() {
        return new OfficeHours();
    }
}
