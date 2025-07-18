package com.ltfreire.bluecard_api.domain.interfaces.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.UpdateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;

public interface IUpdateUserUseCase {
    UserResponseDTO update(String id, UpdateUserRequestDTO request);
}