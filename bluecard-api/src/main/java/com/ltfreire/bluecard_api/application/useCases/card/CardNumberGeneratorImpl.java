package com.ltfreire.bluecard_api.application.useCases.card;

import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.ICardNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class CardNumberGeneratorImpl implements ICardNumberGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String PREFIX = "5321";
    private static final int CARD_NUMBER_LENGTH = 16;
    private final ICardRepository cardRepository;

    public String generateUniqueCardNumber() {
        String cardNumber;
        do {
            cardNumber = generateRandomCardNumber();
        } while (cardRepository.existsByCardNumber(cardNumber));
        return cardNumber;
    }

    private String generateRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder(PREFIX);
        for (int i = PREFIX.length(); i < CARD_NUMBER_LENGTH; i++) {
            int digit = RANDOM.nextInt(10);
            cardNumber.append(digit);
        }
        return cardNumber.toString();
    }
}
