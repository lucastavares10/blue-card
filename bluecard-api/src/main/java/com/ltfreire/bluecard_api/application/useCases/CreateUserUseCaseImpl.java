package com.ltfreire.bluecard_api.application.useCases;

import com.ltfreire.bluecard_api.application.enums.UserRole;
import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.CreateUserResponseDTO;

import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.PasswordEncoderService;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.CreateUserUseCase;
import com.ltfreire.bluecard_api.infra.entity.User;
import com.ltfreire.bluecard_api.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoder;


    public CreateUserResponseDTO create(CreateUserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        User userEntity = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() == UserRole.ADMIN.toString() ? UserRole.ADMIN : UserRole.CLIENT)
                .build();

        userEntity = userRepository.save(userEntity);

        return CreateUserResponseDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build();
    }

}
