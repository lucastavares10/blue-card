package com.ltfreire.bluecard_api.infra.repository.jpa;

import com.ltfreire.bluecard_api.domain.enums.CardRequestStatus;
import com.ltfreire.bluecard_api.infra.entity.CardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRequestJpaRepository extends JpaRepository<CardRequest, Long> {
    
    List<CardRequest> findByUserId(Long userId);

    List<CardRequest> findByStatus(CardRequestStatus status);
}
