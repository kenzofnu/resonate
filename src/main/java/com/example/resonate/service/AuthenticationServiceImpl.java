package com.example.resonate.service;

import com.example.resonate.DTO.Authentication.AuthenticationRequest;
import com.example.resonate.DTO.Authentication.AuthenticationResponse;
import com.example.resonate.DTO.User.UserRequestDTO;
import com.example.resonate.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtServicel;


    @Override
    public AuthenticationResponse register(UserRequestDTO request) {
        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
