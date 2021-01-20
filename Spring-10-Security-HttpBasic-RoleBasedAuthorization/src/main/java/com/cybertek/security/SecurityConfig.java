package com.cybertek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
        Spring Security Java Configuration
        1. Create “SecurityConfiguration” class and extend WebSecurityConfigurerAdapter
        2. Add @Configuration Annotation
        3. Add @EnableWebSecurity Annotation
        4. Override protected void configure(AuthenticationManagerBuilder auth) - UserCredentials
            Implement inMemoryAuthentication Provide Username, Password and Role
        5. Override protected void configure(HttpSecurity http) - Access
            authorizeRequests() : request should be authorized
            anyRequest().authenticated() : incoming request should be authenticated
            httpBasic() : perform basic http authentication
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()            // request should be authorized
                .anyRequest().authenticated()   // incoming requests be authenticated
                .and().httpBasic();             // perform basic authentication
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()       // password needs to be encoded
                .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").and()
                .withUser("adel").password(passwordEncoder().encode("adel123")).roles("USER");
    }
}
