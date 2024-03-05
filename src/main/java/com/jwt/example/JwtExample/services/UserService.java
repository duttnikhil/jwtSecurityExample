package com.jwt.example.JwtExample.services;

import com.jwt.example.JwtExample.entities.User;
import com.jwt.example.JwtExample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return userRepository.findAll();

    }
    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByNumber(String number) {
        return userRepository.existsByNumber(number);
    }

    public User getUsersByEmail(String email) {
        return userRepository.getUsersByEmail(email);
    }

    public User getUsersByNumber(String number) {
        return userRepository.getUsersByNumber(number);
    }

}
