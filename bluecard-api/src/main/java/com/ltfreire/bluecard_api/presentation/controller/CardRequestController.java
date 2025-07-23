package com.ltfreire.bluecard_api.presentation.controller;

import com.ltfreire.bluecard_api.domain.dto.card_request.CardRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.card_request.CardRequestResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card_request.ListRequestCardsResponseDTO;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card_request.IListRequestCardsUseCase;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card_request.IRequestCardUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-request")
@RequiredArgsConstructor
public class CardRequestController {

    private final IRequestCardUseCase requestCardUseCase;
    private final IListRequestCardsUseCase listRequestCardsUseCase;

    @PostMapping()
    public ResponseEntity<?> request(@Valid @RequestBody CardRequestDTO request) {
        CardRequestResponseDTO response = requestCardUseCase.request(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<?> list(@Valid @PathVariable("ownerId") String ownerId) {
        ListRequestCardsResponseDTO response = this.listRequestCardsUseCase.listRequestCards(ownerId);
        return ResponseEntity.ok(response);
    }
}
