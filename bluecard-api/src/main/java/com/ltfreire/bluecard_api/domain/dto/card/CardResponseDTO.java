package com.ltfreire.bluecard_api.domain.dto.card;

import com.ltfreire.bluecard_api.domain.enums.CardStatus;
import com.ltfreire.bluecard_api.domain.enums.UserRole;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CardResponseDTO {
    private Long id;
    private String cardNumber;
    private BigDecimal balance;
    private CardStatus status;
    private Long ownerId;
    private LocalDateTime createdAt;
}