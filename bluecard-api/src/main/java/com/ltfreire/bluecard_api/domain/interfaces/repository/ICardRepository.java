package com.ltfreire.bluecard_api.domain.interfaces.repository;

import com.ltfreire.bluecard_api.domain.enums.CardStatus;
import com.ltfreire.bluecard_api.domain.model.CardModel;

import java.util.List;

public interface ICardRepository {
    CardModel save(CardModel cardModel);

    List<CardModel> listByOwnerId(String ownerId);

    boolean existsByCardNumber(String id);

    void updateStatus(String cardNumber, CardStatus status);
}
