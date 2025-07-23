package com.ltfreire.bluecard_api.domain.interfaces.useCases.card_request;

import com.ltfreire.bluecard_api.domain.dto.card_request.CardRequestResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card_request.CardRequestDTO;

public interface IRequestCardUseCase {
    CardRequestResponseDTO request(CardRequestDTO request);
}