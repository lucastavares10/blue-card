package com.ltfreire.bluecard_api.domain.model;


import com.ltfreire.bluecard_api.domain.enums.CardRequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestModel {
    private Long id;
    private Long ownerId;
    private CardRequestStatus status;
    private String reason;
    private LocalDateTime createdAt;
}