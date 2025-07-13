package com.ltfreire.bluecard_api.domain.interfaces.useCases.security;

import com.ltfreire.bluecard_api.infra.entity.User;

public interface JwtUseCase {
    String generateToken(User userEntity);

    String validateToken(String email);
}