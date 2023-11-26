package com.spring.security.springbootsecurity.services;

import com.spring.security.springbootsecurity.config.ApplicationConfig;
import com.spring.security.springbootsecurity.config.jwtService;
import com.spring.security.springbootsecurity.controller.AuthenticationRequest1;
import com.spring.security.springbootsecurity.controller.AuthenticationResponse;
import com.spring.security.springbootsecurity.controller.RegisterRequest1;
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
    public AuthenticationResponse register(RegisterRequest1 request) {
//        First Extract a data from request
        User user=User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

//        Now return a  token to user
        var jwtToken= jwtservice.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken)

                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest1 request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken= jwtservice.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken)

                .build();
    }
}
