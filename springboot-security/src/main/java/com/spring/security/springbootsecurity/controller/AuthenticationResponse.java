package com.spring.security.springbootsecurity.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

// For Return a token to user
public class AuthenticationResponse {
    private String token;
}
