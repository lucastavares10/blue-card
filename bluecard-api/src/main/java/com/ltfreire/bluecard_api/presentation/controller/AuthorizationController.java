package com.ltfreire.bluecard_api.presentation.controller;

import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.IAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltfreire.bluecard_api.domain.dto.security.AuthRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.security.AuthResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private final IAuthorizationService authUseCase;

    public AuthorizationController(IAuthorizationService authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDTO request) {
        AuthResponseDTO response = authUseCase.login(request);
        return ResponseEntity.ok(response);
    }
}
