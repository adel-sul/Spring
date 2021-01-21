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

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                        // request should be authorized
                .antMatchers("index.html")      // individual
                .permitAll()                                // used for URL’s with no security applied for example css, javascript
                .antMatchers("/profile/**")     // directory
                .authenticated()                            // URL we want to protect, requires a user to login
                .antMatchers("/admin/**")
                .hasRole("ADMIN")                           // restrict to single role
                .antMatchers("/management/**")
                .hasAnyRole("ADMIN", "MANAGER")      // allows multiple roles. An alternative is hasAnyAuthority(String... authorities)
                .and().httpBasic();                         // perform basic authentication
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()       // password needs to be encoded
                .withUser("adel").password(passwordEncoder().encode("adel123")).roles("ADMIN").and()
                .withUser("matt").password(passwordEncoder().encode("matt123")).roles("USER").and()
                .withUser("mike").password(passwordEncoder().encode("mike123")).roles("MANAGER");
    }
}
