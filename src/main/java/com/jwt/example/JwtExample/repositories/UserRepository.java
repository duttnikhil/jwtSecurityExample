package com.jwt.example.JwtExample.repositories;

import com.jwt.example.JwtExample.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);

//    public Optional<User> existsByEmail(String email);
//
//    public Optional<User> existsByPhoneNumber(String phoneNumber);

        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
        boolean existsByEmail(@Param("email") String email);

         @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.number = :number")
         boolean existsByNumber(@Param("number") String number);

        User getUsersByEmail(String email);
        User getUsersByNumber(String number);
    }
