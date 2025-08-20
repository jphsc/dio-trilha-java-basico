--liquibase formatted sql
--changeset henry:202508171513xx
--comment: create table board_column

CREATE TABLE IF NOT EXISTS board_column (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	`order` INT NOT NULL,
	kind VARCHAR(7) NOT NULL,
	board_id BIGINT NOT NULL,
	
	CONSTRAINT fk_boardcolumn_board FOREIGN KEY (board_id) REFERENCES board(id) ON DELETE CASCADE,
	CONSTRAINT uk_boarderid_order UNIQUE KEY unique_boardid_order (board_id, `order`)
) ENGINE=InnoDB;


--rollback DROP TABLE board_column