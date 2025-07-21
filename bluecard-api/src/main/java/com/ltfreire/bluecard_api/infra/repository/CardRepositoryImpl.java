package com.ltfreire.bluecard_api.infra.repository;

import com.ltfreire.bluecard_api.domain.enums.CardStatus;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRepository;
import com.ltfreire.bluecard_api.domain.model.CardModel;
import com.ltfreire.bluecard_api.infra.entity.Card;
import com.ltfreire.bluecard_api.infra.mapper.CardMapper;
import com.ltfreire.bluecard_api.infra.repository.jpa.CardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements ICardRepository {

    private final CardJpaRepository jpaRepository;
    private final CardMapper cardMapper;

    @Override
    public CardModel save(CardModel cardModel) {
        Card entity = cardMapper.modelToEntity(cardModel);
        Card saved = jpaRepository.save(entity);
        return cardMapper.entityToModel(saved);
    }


    @Transactional(readOnly = true)
    public List<CardModel> listByOwnerId(String ownerId) {
        return jpaRepository.findByOwnerId(Long.valueOf(ownerId))
                .stream()
                .map(cardMapper::entityToModel)
                .toList();
    }

    @Override
    public boolean existsByCardNumber(String cardNumber) {
        return jpaRepository.existsByCardNumber(cardNumber);
    }

    @Override
    public void updateStatus(String cardNumber, CardStatus status) {
        jpaRepository.updateStatus(cardNumber, status);
    }
}
