package com.spring.security.springbootsecurity.controller;

import com.spring.security.springbootsecurity.entity.user.User;
import com.spring.security.springbootsecurity.repository.UserRepository;
import com.spring.security.springbootsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/auth")
@RequiredArgsConstructor
public class authenticationController {
    private final UserRepository userRepository;
    private final AuthenticationService service;

//    Method  for add a new user
    @PostMapping(value = "/register")
    public ResponseEntity <AuthenticationResponse> register_user(
            @RequestBody RegisterRequest request
    ){
        System.out.println("Hello i am in Controller -1");
        System.out.println(request);
        return ResponseEntity.ok(service.register(request));
    }


    //    Method  for authenticate a user
        @PostMapping("/auth_user")
    public ResponseEntity <AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        System.out.println("Hello i am in Controller -1");
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/all")
    public List<User> getall(){
        List<User> data=userRepository.findAll();
        return data;
    }
}
