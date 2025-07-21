package com.ltfreire.bluecard_api.presentation.controller;

import com.ltfreire.bluecard_api.domain.dto.card.CardResponseDTO;
import com.ltfreire.bluecard_api.domain.dto.card.CardRequestDTO;
import com.ltfreire.bluecard_api.domain.dto.card.ListCardsResponseDTO;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.IBlockCardUseCase;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.ICreateCardUseCase;
import com.ltfreire.bluecard_api.domain.interfaces.useCases.card.IListCardsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {

    private final ICreateCardUseCase createCardUseCase;
    private final IBlockCardUseCase blockCardUseCase;
    private final IListCardsUseCase listCardsUseCase;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CardRequestDTO request) {
        CardResponseDTO response = createCardUseCase.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<?> list(@Valid @PathVariable("ownerId") String ownerId) {
        ListCardsResponseDTO response = this.listCardsUseCase.listCards(ownerId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cardNumber}/block")
    public ResponseEntity<?> block(@Valid @PathVariable("cardNumber") String cardNumber) {
        blockCardUseCase.block(cardNumber);
        return ResponseEntity.ok().build();
    }


}
