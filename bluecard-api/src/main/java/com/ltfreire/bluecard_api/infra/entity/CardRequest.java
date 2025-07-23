package com.ltfreire.bluecard_api.infra.entity;

import com.ltfreire.bluecard_api.domain.enums.CardRequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "card_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CardRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_request_seq")
    @SequenceGenerator(name = "card_request_seq", sequenceName = "CARD_REQUEST_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardRequestStatus status = CardRequestStatus.PENDING;

    @Column()
    private String reason;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
