package com.ltfreire.bluecard_api.domain.dto.card;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardRequestDTO {
    @NotNull(message = "O ID do proprietário (ownerId) é obrigatório")
    private String ownerId;
}
