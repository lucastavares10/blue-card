package com.ltfreire.bluecard_api.application.useCases.card;

import com.ltfreire.bluecard_api.domain.enums.CardStatus;
import com.ltfreire.bluecard_api.domain.exception.EntityNotFoundException;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRepository;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.IBlockCardUseCase;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.user.IDeleteUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockCardUseCaseImpl implements IBlockCardUseCase {

    private final ICardRepository cardRepository;

    public void block(String cardNumber) {
        if (!cardRepository.existsByCardNumber(cardNumber)) {
            throw new EntityNotFoundException("Cartão não encontrado com ID " + cardNumber);
        }

        cardRepository.updateStatus(cardNumber, CardStatus.BLOCKED);
    }
}
