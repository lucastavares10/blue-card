package com.ltfreire.bluecard_api.application.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.IGetUserByIdUseCase;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCaseImpl implements IGetUserByIdUseCase {

    private final IUserRepository userRepository;

    @Override
    public UserResponseDTO getUserById(String id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
