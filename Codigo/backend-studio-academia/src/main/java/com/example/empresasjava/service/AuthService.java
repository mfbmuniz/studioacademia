package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.LoginRequest;
import com.example.empresasjava.models.ResponseEntity.LoginResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    LoginResponse login(LoginRequest authenticationRequest) throws Exception;

    Authentication getAuthenticatedUser();
}
