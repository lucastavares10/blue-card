package com.ltfreire.bluecard_api.infra.repository;

import com.ltfreire.bluecard_api.domain.enums.CardRequestStatus;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRequestRepository;
import com.ltfreire.bluecard_api.domain.model.CardRequestModel;
import com.ltfreire.bluecard_api.infra.entity.CardRequest;
import com.ltfreire.bluecard_api.infra.mapper.CardRequestMapper;
import com.ltfreire.bluecard_api.infra.repository.jpa.CardRequestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CardRequestRepositoryImpl implements ICardRequestRepository {

    private final CardRequestJpaRepository jpaRepository;
    private final CardRequestMapper mapper;

    @Override
    public CardRequestModel save(CardRequestModel cardRequestModel) {
        CardRequest entity = mapper.modelToEntity(cardRequestModel);
        CardRequest saved = jpaRepository.save(entity);
        return mapper.entityToModel(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardRequestModel> listByOwnerId(String userId) {
        return jpaRepository.findByUserId(Long.valueOf(userId))
                .stream()
                .map(mapper::entityToModel)
                .toList();
    }

    @Override
    public Optional<CardRequestModel> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void updateStatus(String requestId, CardRequestStatus status) {
        CardRequest request = jpaRepository.findById(Long.valueOf(requestId))
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
        request.setStatus(status);
        jpaRepository.save(request);
    }
}
