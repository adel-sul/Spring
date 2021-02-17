package com.cybertek.controller;

import com.cybertek.entity.AccountDetails;
import com.cybertek.entity.UserAccount;
import com.cybertek.repository.AccountRepository;
import com.cybertek.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    UserRepository userRepository;
    AccountRepository accountRepository;

    public HomeController(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/users")
    public List<UserAccount> readAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/accounts")
    public List<AccountDetails> readAllAccounts() {
        return accountRepository.findAll();
    }
}
