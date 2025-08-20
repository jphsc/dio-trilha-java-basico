--liquibase formatted sql
--changeset henry:202508171530xx
--comment: create table cards

CREATE TABLE IF NOT EXISTS cards (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	board_column_id BIGINT NOT NULL,
	
	CONSTRAINT fk_boardcolumn_cards FOREIGN KEY (board_column_id) REFERENCES board_column(id) ON DELETE CASCADE
) ENGINE=InnoDB;


--rollback DROP TABLE cards