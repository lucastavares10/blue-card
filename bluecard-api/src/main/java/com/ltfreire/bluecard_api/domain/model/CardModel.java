package com.ltfreire.bluecard_api.domain.model;

import com.ltfreire.bluecard_api.domain.enums.CardStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CardModel {
    private Long id;
    private String cardNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    private CardStatus status = CardStatus.ACTIVE;
    private UserModel owner;
    private LocalDateTime createdAt;
}