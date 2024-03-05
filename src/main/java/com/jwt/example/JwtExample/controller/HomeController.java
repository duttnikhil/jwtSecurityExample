package com.jwt.example.JwtExample.controller;

import com.jwt.example.JwtExample.entities.User;
import com.jwt.example.JwtExample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;


    //http://localhost:8081/home/users
    @GetMapping("/users")
    public List<User> getUser(){

        System.out.println("Getting Users");
        return this.userService.getUsers();
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

    @GetMapping("/user-by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        // Retrieve user by email
        User user = userService.getUsersByEmail(email);

        if (user != null) {
            // User found, return user details
            return ResponseEntity.ok(user);
        } else {
            // User not found with the provided email
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the provided email");
        }
    }
    @GetMapping("/user-by-number/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        // Retrieve user by phone number
        User user = userService.getUsersByNumber(phoneNumber);

        if (user != null) {
            // User found, return user details
            return ResponseEntity.ok(user);
        } else {
            // User not found with the provided phone number
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the provided phone number");
        }
    }



}
