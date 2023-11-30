package com.spring.security.springbootsecurity.controller;


import com.spring.security.springbootsecurity.entity.user.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
