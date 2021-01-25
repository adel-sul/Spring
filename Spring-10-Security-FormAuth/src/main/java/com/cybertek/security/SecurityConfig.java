package com.cybertek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                        // request should be authorized
                .antMatchers("/profile/**")     // directory
                .authenticated()                            // URL we want to protect, requires a user to login
                .antMatchers("/admin/**")
                .hasRole("ADMIN")                           // restrict to single role
                .antMatchers("/management/**")
                .hasAnyAuthority("ADMIN", "MANAGER")      // allows multiple roles. An alternative is hasAnyAuthority(String... authorities)
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true")                     // for failed log in
                .permitAll()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout=true")
                .and().rememberMe().tokenValiditySeconds(120)        // how long to keep user in the system
                .key("cybertekSecret")
                .userDetailsService(userPrincipalDetailsService);

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
