package com.ltfreire.bluecard_api.application.useCases;

import com.ltfreire.bluecard_api.application.useCases.user.CreateUserUseCaseImpl;
import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.UserResponseDTO;
import com.ltfreire.bluecard_api.domain.enums.UserRole;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.security.IPasswordEncoderService;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import com.ltfreire.bluecard_api.infra.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseImplTest {

    private final UserRepositoryImpl userRepositoryImpl = mock(UserRepositoryImpl.class);
    private final IPasswordEncoderService passwordEncoder = mock(IPasswordEncoderService.class);
    private final CreateUserUseCaseImpl userUseCase = new CreateUserUseCaseImpl(userRepositoryImpl, passwordEncoder);

    @Test
    void deveCriarUsuarioComSucesso() {
        CreateUserRequestDTO request = new CreateUserRequestDTO();
        request.setName("Lucas");
        request.setEmail("lucas@email.com");
        request.setPassword("123456");
        request.setRole(UserRole.CLIENT);

        when(userRepositoryImpl.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode("123456")).thenReturn("hashedPassword");

        UserModel savedUser = UserModel.builder()
                .id(123456789L)
                .name(request.getName())
                .email(request.getEmail())
                .password("hashedPassword")
                .role(UserRole.CLIENT)
                .build();

        when(userRepositoryImpl.save(any(UserModel.class))).thenReturn(savedUser);


        UserResponseDTO response = userUseCase.create(request);


        assertNotNull(response);
        assertEquals("Lucas", response.getName());
        assertEquals("lucas@email.com", response.getEmail());
        assertEquals(UserRole.CLIENT, response.getRole());
        verify(userRepositoryImpl, times(1)).save(any(UserModel.class));
    }

    @Test
    void deveLancarExcecao_QuandoEmailJaExiste() {
        CreateUserRequestDTO request = new CreateUserRequestDTO();
        request.setEmail("existente@email.com");

        when(userRepositoryImpl.existsByEmail(request.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userUseCase.create(request);
        });

        assertEquals("Email já cadastrado", exception.getMessage());
        verify(userRepositoryImpl, never()).save(any());
    }
}
