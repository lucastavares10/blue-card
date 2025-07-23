package com.ltfreire.bluecard_api.application.useCases.card;

import com.ltfreire.bluecard_api.domain.dto.card.CardRequestResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card.CardRequestDTO;
import com.ltfreire.bluecard_api.domain.enums.CardRequestStatus;
import com.ltfreire.bluecard_api.domain.exception.EntityNotFoundException;
import com.ltfreire.bluecard_api.domain.interfaces.repository.ICardRequestRepository;
import com.ltfreire.bluecard_api.domain.interfaces.repository.IUserRepository;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.IRequestCardUseCase;
import com.ltfreire.bluecard_api.domain.model.CardRequestModel;
import com.ltfreire.bluecard_api.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestCardUseCaseImpl implements IRequestCardUseCase {

    private final KafkaTemplate<String, CardRequestModel> kafkaTemplate;
    private final IUserRepository userRepository;
    private final ICardRequestRepository cardRequestRepository;


    public CardRequestResponseDTO request(CardRequestDTO request) {
        UserModel owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID " + request.getOwnerId()));

        CardRequestModel cardRequest = CardRequestModel.builder()
                .ownerId(Long.valueOf(request.getOwnerId()))
                .reason("Request initiated")
                .status(CardRequestStatus.PENDING)
                .build();

        CardRequestModel saved = cardRequestRepository.save(cardRequest);

        kafkaTemplate.send("card-requests", cardRequest);

        return CardRequestResponseDTO.builder()
                .id(saved.getId())
                .ownerId(saved.getOwnerId())
                .createdAt(saved.getCreatedAt())
                .status(saved.getStatus())
                .reason(saved.getReason())
                .build();
    }

}
