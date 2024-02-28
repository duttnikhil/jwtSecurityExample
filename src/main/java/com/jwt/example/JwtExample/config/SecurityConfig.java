package com.jwt.example.JwtExample.config;

import com.jwt.example.JwtExample.security.JwtAuthenticationEntryPoint;
import com.jwt.example.JwtExample.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/home/**")
                                .authenticated()
                                .requestMatchers("/auth/login").permitAll()
                                .anyRequest().authenticated()).exceptionHandling(ex->ex.authenticationEntryPoint(point))
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // after auth.requestMatchers put .hasRole("ADMIN") and remove authenticated for only accessable by admin
        //read about csrf
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();



    }

}
