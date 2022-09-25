package com.example.empresasjava.controller;

import com.example.empresasjava.models.RequestEntity.LoginRequest;
import com.example.empresasjava.models.ResponseEntity.LoginResponse;
import com.example.empresasjava.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticateController {

	@Autowired
	private AuthService authService;


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> createAuthenticationToken(
			@RequestBody LoginRequest authenticationRequest) throws Exception {

		return ResponseEntity.ok(this.authService.login(authenticationRequest));

	}
}
