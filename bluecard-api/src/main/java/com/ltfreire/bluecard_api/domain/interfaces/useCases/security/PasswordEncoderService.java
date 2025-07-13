package com.ltfreire.bluecard_api.domain.interfaces.useCases.security;

public interface PasswordEncoderService {
    String encode(String rawPassword);
}
