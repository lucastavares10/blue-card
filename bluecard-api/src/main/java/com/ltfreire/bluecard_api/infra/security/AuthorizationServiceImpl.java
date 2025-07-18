package com.ltfreire.bluecard_api.infra.security;

import com.ltfreire.bluecard_api.domain.dto.security.AuthRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.security.AuthResponseDTO;

import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.IAuthorizationService;
import com.ltfreire.bluecard_api.infra.entity.User;


import com.ltfreire.bluecard_api.infra.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final UserMapper userMapper;

    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authToken);

        var user = (User) authentication.getPrincipal();

        var userModel = this.userMapper.entityToModel(user);

        String token = jwtService.generateToken(userModel);

        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }
}
