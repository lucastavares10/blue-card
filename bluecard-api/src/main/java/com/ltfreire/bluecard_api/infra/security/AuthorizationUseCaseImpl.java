package com.ltfreire.bluecard_api.infra.security;

import com.ltfreire.bluecard_api.domain.dto.security.AuthRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.security.AuthResponseDTO;

import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.AuthorizationUseCase;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.JwtUseCase;
import com.ltfreire.bluecard_api.infra.entity.User;
import com.ltfreire.bluecard_api.infra.repository.UserRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthorizationUseCaseImpl implements AuthorizationUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtUseCase jwtUseCase;

    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        var user = (User) authentication.getPrincipal();

        String token = jwtUseCase.generateToken(user);

        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }
}
