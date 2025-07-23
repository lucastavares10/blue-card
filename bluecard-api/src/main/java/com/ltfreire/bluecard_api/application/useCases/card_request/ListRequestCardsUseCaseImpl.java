package com.ltfreire.bluecard_api.application.useCases.card_request;

import com.ltfreire.bluecard_api.domain.dto.card.CardResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card.ListCardsResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card_request.CardRequestResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card_request.ListRequestCardsResponseDTO;
import com.ltfreire.bluecard_api.domain.exception.EntityNotFoundException;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRepository;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRequestRepository;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.IListCardsUseCase;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card_request.IListRequestCardsUseCase;
import com.ltfreire.bluecard_api.domain.model.CardModel;
import com.ltfreire.bluecard_api.domain.model.CardRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListRequestCardsUseCaseImpl implements IListRequestCardsUseCase {

    private final ICardRequestRepository requestCardRepository;
    private final IUserRepository userRepository;

    public ListRequestCardsResponseDTO listRequestCards(String ownerId) {
        if (!userRepository.existsById(ownerId)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID " + ownerId);
        }

        List<CardRequestModel> requestCards = requestCardRepository.listByOwnerId(ownerId);

        var requestCardsResponse = requestCards.stream()
                .map(requestCard -> CardRequestResponseDTO.builder()
                        .id(requestCard.getId())
                        .ownerId(requestCard.getOwnerId())
                        .status(requestCard.getStatus())
                        .reason(requestCard.getReason())
                        .createdAt(requestCard.getCreatedAt())
                        .build()).toList();

        return ListRequestCardsResponseDTO.builder().requestCards(requestCardsResponse).build();
    }
}
