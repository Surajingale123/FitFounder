package com.example.demo.service;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;

public interface IAuthenticationService {
    User register(RegisterRequest registerRequest);
    public JwtAuthenticationResponse login(LoginRequest loginRequest);
}
