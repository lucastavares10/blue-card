package com.ltfreire.bluecard_api.domain.dto.user;

import com.ltfreire.bluecard_api.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequestDTO {

    @Size(min = 3, max = 100, message = "O nome deve ter no mínimo 3 caracteres")
    @NotBlank
    private String name;

    @Email(message = "Email inválido")
    @NotBlank
    private String email;

    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @NotBlank
    private String password;

    @NotNull(message = "O papel do usuário é obrigatório")
    private UserRole role;
}
