package com.ltfreire.bluecard_api.domain.interfaces.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;

import java.util.ArrayList;

public interface IListUsersUseCase {
    ArrayList<UserResponseDTO> listUsers(CreateUserRequestDTO request);
}