package com.ltfreire.bluecard_api.presentation.controller;

import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.ListUsersResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UpdateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ICreateUserUseCase createUserUseCase;
    private final IListUsersUseCase listUsersUseCase;
    private final IGetUserByIdUseCase getUserByIdUseCase;
    private final IUpdateUserUseCase updateUserUseCase;
    private final IDeleteUserUseCase deleteUserUseCase;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserRequestDTO request) {
        try {
            UserResponseDTO response = createUserUseCase.create(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> list() {
        try {
            ListUsersResponseDTO response = this.listUsersUseCase.listUsers();
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            UserResponseDTO response = this.getUserByIdUseCase.getUserById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateUserRequestDTO request, @PathVariable String id) {
        try {
            UserResponseDTO response = this.updateUserUseCase.update(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            this.deleteUserUseCase.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
