package com.ltfreire.bluecard_api.domain.interfaces.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;

public interface IGetUserByIdUseCase {
    UserResponseDTO getUserById(String id);
}