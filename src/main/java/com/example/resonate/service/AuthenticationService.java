package com.example.resonate.service;

import com.example.resonate.DTO.Authentication.AuthenticationRequest;
import com.example.resonate.DTO.Authentication.AuthenticationResponse;
import com.example.resonate.DTO.User.UserRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(UserRequestDTO request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
