package com.ltfreire.bluecard_api.domain.interfaces.useCases.card;

import com.ltfreire.bluecard_api.domain.dto.card.ListCardsResponseDTO;

public interface IListCardsUseCase {
    ListCardsResponseDTO listCards(String cardNumber);
}