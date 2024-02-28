package com.jwt.example.JwtExample.services;

import com.jwt.example.JwtExample.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> store = new ArrayList<>();

    public UserService() {
        store.add(new User(UUID.randomUUID().toString(),"Nikhil Dutt","nikhildutt111@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Hello World","hello@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Prashant Bhushan","prashant@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Piyush Beta","piyush@gmail.com"));
    }
    public List<User> getUsers(){
        return this.store;

    }
}
