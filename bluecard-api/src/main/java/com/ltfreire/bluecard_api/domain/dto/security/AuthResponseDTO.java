package com.ltfreire.bluecard_api.domain.dto.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String token;
}
