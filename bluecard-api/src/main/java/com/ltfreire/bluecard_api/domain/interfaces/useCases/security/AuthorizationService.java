package com.ltfreire.bluecard_api.domain.interfaces.useCases.security;

import org.springframework.security.core.userdetails.UserDetails;


public interface AuthorizationService {
    UserDetails loadUserByUsername(String email);
    boolean isEmailRegistered(String email);
}