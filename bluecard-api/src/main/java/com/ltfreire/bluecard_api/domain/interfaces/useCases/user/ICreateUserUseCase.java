package com.ltfreire.bluecard_api.domain.interfaces.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;

public interface ICreateUserUseCase {
    UserResponseDTO create(CreateUserRequestDTO request);
}