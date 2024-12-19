package com.example.demo.controllers;

import com.example.demo.dtos.RegisterDTO;
import com.example.demo.dtos.LoginResponseDTO;
import com.example.demo.dtos.LoginDTO;
import com.example.demo.service.TokenService;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenService tokenService;

    public AuthController(AuthService authService, AuthenticationManagerBuilder authenticationManagerBuilder,
                          TokenService tokenService) {
        this.authService = authService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());

        // Call the authenticate method of the AuthenticationManagerBuilder instance
        // to authenticate the user
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenService.generateAccessToken(authentication);

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setAccessToken(accessToken);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@Valid @RequestBody RegisterDTO registerDTO) {
        if (authService.existsByUsername(registerDTO.getUsername())) {
            return ResponseEntity.badRequest().body(false);
        }

        var result = authService.createUser(registerDTO);

        return ResponseEntity.ok(result != null);
    }
}
