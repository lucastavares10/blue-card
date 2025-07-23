package com.ltfreire.bluecard_api.domain.interfaces.useCases.card;

import com.ltfreire.bluecard_api.domain.dto.card.CardRequestResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card.CardRequestDTO;

public interface IRequestCardUseCase {
    CardRequestResponseDTO request(CardRequestDTO request);
}