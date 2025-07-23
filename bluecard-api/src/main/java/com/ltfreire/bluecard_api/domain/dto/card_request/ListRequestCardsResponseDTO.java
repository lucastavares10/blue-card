package com.ltfreire.bluecard_api.domain.dto.card_request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListRequestCardsResponseDTO {
    private List<CardRequestResponseDTO> requestCards;
    private int count;
}