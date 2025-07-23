package com.ltfreire.bluecard_api.domain.interfaces.useCases.card_request;

import com.ltfreire.bluecard_api.domain.dto.card_request.ListRequestCardsResponseDTO;

public interface IListRequestCardsUseCase {
    ListRequestCardsResponseDTO listRequestCards(String ownerId);
}