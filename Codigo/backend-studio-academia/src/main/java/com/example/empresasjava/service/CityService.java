package com.example.empresasjava.service;

import com.example.empresasjava.models.Cities;
import com.example.empresasjava.models.RequestEntity.LoginRequest;
import com.example.empresasjava.models.ResponseEntity.LoginResponse;
import org.springframework.security.core.Authentication;

public interface CityService {

    Cities findByCity(String city);
}
