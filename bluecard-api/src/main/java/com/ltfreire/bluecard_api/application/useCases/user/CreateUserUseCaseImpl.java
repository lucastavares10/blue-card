package com.ltfreire.bluecard_api.application.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;

import com.ltfreire.bluecard_api.domain.enums.UserRole;
import com.ltfreire.bluecard_api.domain.exception.EmailAlreadyExistsException;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.IPasswordEncoderService;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.ICreateUserUseCase;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements ICreateUserUseCase {

    private final IUserRepository userRepository;
    private final IPasswordEncoderService passwordEncoder;


    public UserResponseDTO create(CreateUserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }

        UserModel userModel = UserModel.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().equals(UserRole.ADMIN) ? UserRole.ADMIN : UserRole.CLIENT)
                .build();

        userModel = userRepository.save(userModel);

        return UserResponseDTO.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .role(userModel.getRole())
                .build();
    }

}
