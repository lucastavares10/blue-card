package com.ltfreire.bluecard_api.application.useCases.card;

import com.ltfreire.bluecard_api.domain.dto.card.CardResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card.ListCardsResponseDTO;
import com.ltfreire.bluecard_api.domain.exception.EntityNotFoundException;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRepository;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.IListCardsUseCase;
import com.ltfreire.bluecard_api.domain.model.CardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCardsUseCaseImpl implements IListCardsUseCase {

    private final ICardRepository cardRepository;
    private final IUserRepository userRepository;

    public ListCardsResponseDTO listCards(String ownerId) {
        if (!userRepository.existsById(ownerId)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID " + ownerId);
        }

        List<CardModel> cards = cardRepository.listByOwnerId(ownerId);

        var cardsResponse = cards.stream()
                .map(card -> CardResponseDTO.builder()
                        .id(card.getId())
                        .cardNumber(card.getCardNumber())
                        .balance(card.getBalance())
                        .status(card.getStatus())
                        .ownerId(card.getOwner().getId())
                        .build()).toList();

        return ListCardsResponseDTO.builder().cards(cardsResponse).build();
    }
}
