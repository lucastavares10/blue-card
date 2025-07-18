package com.ltfreire.bluecard_api.domain.interfaces.useCases.security;

public interface IPasswordEncoderService {
    String encode(String rawPassword);
}
