package com.ltfreire.bluecard_api.application.useCases.user;

import com.ltfreire.bluecard_api.application.utils.NullPropertyUtils;
import com.ltfreire.bluecard_api.domain.dto.user.UpdateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.IPasswordEncoderService;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.IUpdateUserUseCase;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements IUpdateUserUseCase {

    private final IUserRepository userRepository;
    private final IPasswordEncoderService passwordEncoder;


    public UserResponseDTO update(String id, UpdateUserRequestDTO request) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (request.getPassword() != null) {
            request.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        NullPropertyUtils.copyNonNullProperties(request, userModel);

        userModel = userRepository.save(userModel);

        return UserResponseDTO.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .role(userModel.getRole())
                .build();
    }

}
