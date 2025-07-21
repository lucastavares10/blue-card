-- ==========================================
-- SEQUENCES
-- ==========================================
CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE CARD_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE TRANSACTION_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE TOPUP_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

-- ==========================================
-- TABELA USERS
-- ==========================================
CREATE TABLE users (
    id         NUMBER(19) PRIMARY KEY,
    name       VARCHAR2(255) NOT NULL,
    email      VARCHAR2(100) NOT NULL UNIQUE,
    password   VARCHAR2(255) NOT NULL,
    role       VARCHAR2(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT chk_users_role CHECK (role IN ('ADMIN', 'CLIENT'))
);

-- ==========================================
-- TABELA CARDS
-- ==========================================
CREATE TABLE cards (
    id     NUMBER PRIMARY KEY,
    owner_id    NUMBER NOT NULL,
    number      VARCHAR2(16) NOT NULL UNIQUE,
    balance     NUMBER(10, 2) DEFAULT 0 NOT NULL,
    status      VARCHAR2(20) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_cards_owner FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT chk_cards_status CHECK (status IN ('ACTIVE', 'BLOCKED'))
);

-- ==========================================
-- TABELA TRANSACTIONS
-- ==========================================
CREATE TABLE transactions (
    id NUMBER PRIMARY KEY,
    card_id        NUMBER NOT NULL,
    type           VARCHAR2(20) NOT NULL,
    amount         NUMBER(10, 2) NOT NULL,
    description    VARCHAR2(255),
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_transactions_card FOREIGN KEY (card_id) REFERENCES cards(card_id) ON DELETE CASCADE,
    CONSTRAINT chk_transactions_type CHECK (type IN ('TOP_UP', 'PAYMENT', 'ADJUSTMENT'))
);

-- ==========================================
-- TABELA TOP_UPS
-- ==========================================
CREATE TABLE top_ups (
    id    NUMBER PRIMARY KEY,
    card_id      NUMBER NOT NULL,
    amount       NUMBER(10, 2) NOT NULL,
    status       VARCHAR2(20) NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_topups_card FOREIGN KEY (card_id) REFERENCES cards(card_id) ON DELETE CASCADE,
    CONSTRAINT chk_topups_status CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED'))
);

