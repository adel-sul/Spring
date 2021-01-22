package com.cybertek.bootstrap;

import com.cybertek.entity.User;
import com.cybertek.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DataGenerator implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DataGenerator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // Delete All
        this.userRepository.deleteAll();

        // Create Users
        User employee = new User("matt", passwordEncoder.encode("matt123"), "USER", "");
        User admin = new User("adel", passwordEncoder.encode("adel123"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
        User manager = new User("mike", passwordEncoder.encode("mike123"), "MANAGER", "ACCESS_TEST1");

        List<User> users = Arrays.asList(employee, admin, manager);
        userRepository.saveAll(users);

    }
}
