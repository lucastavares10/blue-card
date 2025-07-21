package com.ltfreire.bluecard_api.domain.interfaces.useCases.card;

import com.ltfreire.bluecard_api.domain.dto.card.CardResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card.CardRequestDTO;

public interface ICreateCardUseCase {
    CardResponseDTO create(CardRequestDTO request);
}