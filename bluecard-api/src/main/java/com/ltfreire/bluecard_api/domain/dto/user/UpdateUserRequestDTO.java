package com.ltfreire.bluecard_api.domain.dto.user;

import com.ltfreire.bluecard_api.application.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {

    @Size(min = 3, max = 100, message = "O nome deve ter no mínimo 3 caracteres")
    private String name;

    @Email(message = "Email inválido")
    private String email;

    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    private UserRole role;
}
