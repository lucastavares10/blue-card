package com.ltfreire.bluecard_api.domain.dto.card;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListCardsResponseDTO {
    private List<CardResponseDTO> cards;
    private int count;
}