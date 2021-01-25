package com.cybertek.security;

import com.cybertek.entity.User;
import com.cybertek.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(name);
        if (user==null) { throw new UsernameNotFoundException("USER DOES NOT EXIST"); }
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}
