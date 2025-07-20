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
        UserResponseDTO response = createUserUseCase.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<?> list() {
        ListUsersResponseDTO response = this.listUsersUseCase.listUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        UserResponseDTO response = this.getUserByIdUseCase.getUserById(id);
        return ResponseEntity.ok(response);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateUserRequestDTO request, @PathVariable String id) {
        UserResponseDTO response = this.updateUserUseCase.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        this.deleteUserUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

}
