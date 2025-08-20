--liquibase formatted sql
--changeset henry:202508171540xx
--comment: create table block

CREATE TABLE IF NOT EXISTS block (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	blocked_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	block_reason VARCHAR(255) NOT NULL,
	unblocked_at TIMESTAMP NULL,
	unblock_reason VARCHAR(255) NOT NULL,
	card_id BIGINT NOT NULL,
	
	CONSTRAINT fk_cards_block FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE
) ENGINE=InnoDB;


--rollback DROP TABLE block