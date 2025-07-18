package com.ltfreire.bluecard_api.domain.interfaces.useCases.security;

import com.ltfreire.bluecard_api.domain.model.UserModel;

public interface IJwtService {
    String generateToken(UserModel user);
    String validateToken(String email);
}