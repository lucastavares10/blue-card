package com.ltfreire.bluecard_api.domain.interfaces.repository;

import com.ltfreire.bluecard_api.domain.enums.CardRequestStatus;
import com.ltfreire.bluecard_api.domain.model.CardRequestModel;

import java.util.List;
import java.util.Optional;

public interface ICardRequestRepository {
    CardRequestModel save(CardRequestModel cardModel);

    List<CardRequestModel> listByOwnerId(String ownerId);

    Optional<CardRequestModel> findById(String id);

    void updateStatus(String id, CardRequestStatus status);
}
