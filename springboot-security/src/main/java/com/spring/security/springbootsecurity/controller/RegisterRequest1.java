package com.spring.security.springbootsecurity.controller;


import com.spring.security.springbootsecurity.entity.user.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor

public class RegisterRequest1 {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
