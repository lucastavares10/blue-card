package com.ltfreire.bluecard_api.domain.interfaces.useCases.security;

import com.ltfreire.bluecard_api.domain.dto.security.AuthRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.security.AuthResponseDTO;

public interface AuthorizationUseCase {


    AuthResponseDTO login(AuthRequestDTO request);
}