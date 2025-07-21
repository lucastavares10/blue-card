package com.ltfreire.bluecard_api.infra.repository.jpa;

import com.ltfreire.bluecard_api.domain.enums.CardStatus;
import com.ltfreire.bluecard_api.infra.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CardJpaRepository extends JpaRepository<Card, Long> {

    boolean existsByCardNumber(String cardNumber);

    List<Card> findByOwnerId(Long ownerId);

    @Transactional
    @Modifying
    @Query("UPDATE Card c SET c.status = :status WHERE c.cardNumber = :cardNumber")
    void updateStatus(@Param("cardNumber") String cardNumber, @Param("status") CardStatus status);
}
