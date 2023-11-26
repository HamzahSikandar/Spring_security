package com.spring.security.springbootsecurity.controller;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class AuthenticationRequest1 {
    private String email;
    String password;
}
