package com.spring.security.springbootsecurity.services;

import com.spring.security.springbootsecurity.config.jwtService;
import com.spring.security.springbootsecurity.controller.AuthenticationRequest;
import com.spring.security.springbootsecurity.controller.AuthenticationResponse;
import com.spring.security.springbootsecurity.controller.RegisterRequest;
import com.spring.security.springbootsecurity.entity.user.Role;
import com.spring.security.springbootsecurity.entity.user.User;
import com.spring.security.springbootsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final jwtService jwtservice;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
//        First Extract a data from request
        System.out.println("I am inside register service-2");
        User user=User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();


        System.out.println("I  am  after set values -3");

        userRepository.save(user);
        System.out.println("I  am  after save values -4");

//        Now return a  token to user
        var jwtToken= jwtservice.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken)

                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("I am step-2");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("I am step-3");
        var user=userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.println("I am step-4");
        var jwtToken= jwtservice.generateToken(user);
        System.out.println("I am step-5");
        return AuthenticationResponse.builder().token(jwtToken)

                .build();
    }
}
