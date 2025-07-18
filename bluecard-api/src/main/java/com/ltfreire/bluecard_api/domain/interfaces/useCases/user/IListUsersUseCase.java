package com.ltfreire.bluecard_api.domain.interfaces.useCases.user;

import com.ltfreire.bluecard_api.domain.dto.user.ListUsersResponseDTO;

public interface IListUsersUseCase {
    ListUsersResponseDTO listUsers();
}