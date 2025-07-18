package com.ltfreire.bluecard_api.presentation.controller;

import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.ICreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final ICreateUserUseCase ICreateUserUseCase;

    public UserController(ICreateUserUseCase ICreateUserUseCase) {
        this.ICreateUserUseCase = ICreateUserUseCase;
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserRequestDTO request) {
        try {
            UserResponseDTO response = ICreateUserUseCase.create(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
