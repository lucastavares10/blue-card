package com.ltfreire.bluecard_api.application.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.ListUsersResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.IListUsersUseCase;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListUsersUseCaseImpl implements IListUsersUseCase {

    private final IUserRepository userRepository;

    public ListUsersResponseDTO listUsers() {
        List<UserModel> users = userRepository.findAll();

        var usersResponse = users.stream()
                .map(user -> UserResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));

        return ListUsersResponseDTO.builder().users(usersResponse).build();
    }
}
