CREATE SEQUENCE CARD_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE TABLE cards (
    id          NUMBER(19) PRIMARY KEY,
    owner_id    NUMBER NOT NULL,
    card_number VARCHAR2(16) NOT NULL UNIQUE,
    balance     NUMBER(10, 2) DEFAULT 0 NOT NULL,
    status      VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_cards_owner FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT chk_cards_status CHECK (status IN ('ACTIVE', 'BLOCKED'))
);
