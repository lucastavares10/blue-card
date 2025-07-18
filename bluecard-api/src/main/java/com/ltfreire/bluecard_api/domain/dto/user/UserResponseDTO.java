package com.ltfreire.bluecard_api.domain.dto.user;

import com.ltfreire.bluecard_api.application.enums.UserRole;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private UserRole role;
}