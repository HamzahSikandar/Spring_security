package com.spring.security.springbootsecurity.controller;

import com.spring.security.springbootsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/auth")
@RequiredArgsConstructor
public class authenticationController {
    private final AuthenticationService service;

//    Method  for add a new user
    @PostMapping("/register")
    public ResponseEntity <AuthenticationResponse> register(
            @RequestBody RegisterRequest1 request
    ){
        return ResponseEntity.ok(service.register(request));
    }


    //    Method  for authenticate a user
    @PostMapping("/register")
    public ResponseEntity <AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest1 request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
