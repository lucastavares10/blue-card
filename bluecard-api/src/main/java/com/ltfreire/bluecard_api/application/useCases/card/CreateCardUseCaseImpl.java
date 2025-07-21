package com.ltfreire.bluecard_api.application.useCases.card;

import com.ltfreire.bluecard_api.domain.dto.card.CardResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card.CardRequestDTO;
import com.ltfreire.bluecard_api.domain.enums.CardStatus;
import com.ltfreire.bluecard_api.domain.exception.EntityNotFoundException;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRepository;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.ICardNumberGenerator;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.ICreateCardUseCase;
import com.ltfreire.bluecard_api.domain.model.CardModel;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateCardUseCaseImpl implements ICreateCardUseCase {

    private final IUserRepository userRepository;
    private final ICardRepository cardRepository;
    private final ICardNumberGenerator cardNumberGenerator;


    public CardResponseDTO create(CardRequestDTO request) {
        UserModel owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID " + request.getOwnerId()));


        CardModel card = CardModel.builder()
                .cardNumber(cardNumberGenerator.generateUniqueCardNumber())
                .balance(BigDecimal.ZERO)
                .status(CardStatus.ACTIVE)
                .owner(owner)
                .createdAt(LocalDateTime.now())
                .build();

        CardModel saved = cardRepository.save(card);

        return CardResponseDTO.builder()
                .id(saved.getId())
                .cardNumber(saved.getCardNumber())
                .balance(saved.getBalance())
                .status(saved.getStatus())
                .createdAt(saved.getCreatedAt())
                .ownerId(saved.getOwner().getId())
                .build();
    }

}
