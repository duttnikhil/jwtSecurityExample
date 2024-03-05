package com.jwt.example.JwtExample.controller;

import com.jwt.example.JwtExample.entities.User;
import com.jwt.example.JwtExample.models.JwtRequest;
import com.jwt.example.JwtExample.models.JwtResponse;
import com.jwt.example.JwtExample.security.JwtHelper;
import com.jwt.example.JwtExample.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        // Authenticate user
        this.doAuthenticate(request.getEmail(), request.getPassword());

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        // Generate token
        String token = this.helper.generateToken(userDetails);

        // Create JwtResponse with token and user details
        JwtResponse response = new JwtResponse(token, userDetails.getUsername(), userDetails.getAuthorities());

        // Return response with token and user details
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Check if a user with the given phone number or email already exists
        boolean phoneNumberExists = userService.existsByNumber(user.getNumber());
        boolean emailExists = userService.existsByEmail(user.getEmail());

        if (!phoneNumberExists && !emailExists) {
            // User doesn't exist, create user
            User createdUser = userService.createUser(user);
            // Generate token (you need to implement this)
            String token = this.helper.generateToken(createdUser);
            // Return user details and token
            return ResponseEntity.ok(new JwtResponse(createdUser, token));
        } else {
            // User with the provided phone number or email already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with the provided phone number or email already exists");
        }
    }




}
