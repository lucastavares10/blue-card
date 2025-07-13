package com.ltfreire.bluecard_api.application.useCases;

import com.ltfreire.bluecard_api.application.enums.UserRole;
import com.ltfreire.bluecard_api.domain.dto.user.CreateUserRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.user.CreateUserResponseDTO;
import com.ltfreire.bluecard_api.infra.entity.User;
import com.ltfreire.bluecard_api.infra.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseImplTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final CreateUserUseCaseImpl userUseCase = new CreateUserUseCaseImpl(userRepository, passwordEncoder);

    @Test
    void deveCriarUsuarioComSucesso() {
        CreateUserRequestDTO request = new CreateUserRequestDTO();
        request.setName("Lucas");
        request.setEmail("lucas@email.com");
        request.setPassword("123456");
        request.setRole("CLIENT");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode("123456")).thenReturn("hashedPassword");

        User savedUser = User.builder()
                .id(123456789L)
                .name(request.getName())
                .email(request.getEmail())
                .password("hashedPassword")
                .role(UserRole.CLIENT)
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);


        CreateUserResponseDTO response = userUseCase.create(request);


        assertNotNull(response);
        assertEquals("Lucas", response.getName());
        assertEquals("lucas@email.com", response.getEmail());
        assertEquals(UserRole.CLIENT, response.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deveLancarExcecao_QuandoEmailJaExiste() {
        CreateUserRequestDTO request = new CreateUserRequestDTO();
        request.setEmail("existente@email.com");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userUseCase.create(request);
        });

        assertEquals("Email já cadastrado", exception.getMessage());
        verify(userRepository, never()).save(any());
    }
}
