package com.ltfreire.bluecard_api.domain.dto.card_request;

import com.ltfreire.bluecard_api.domain.enums.CardRequestStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CardRequestResponseDTO {
    private Long id;
    private CardRequestStatus status;
    private Long ownerId;
    private String reason;
    private LocalDateTime createdAt;
}