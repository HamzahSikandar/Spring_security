package com.spring.security.springbootsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/demo")
public class demoController {
    public ResponseEntity<String> Sayello(){
        return ResponseEntity.ok("Hello Welcome to My academy");

    }
}
