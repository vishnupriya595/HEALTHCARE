package com.example.HealthCare.Service;

import com.example.HealthCare.Model.Users;
import com.example.HealthCare.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user1 = userRepository.findByUsername(username);
        if (user1 == null) {
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user1.getUsername())
                .password(user1.getPassword())
                .roles(user1.getRole().name())
                .build();
    }

}
