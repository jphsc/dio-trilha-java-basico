CREATE TABLE IF NOT EXISTS contact (
	id BIGINT NOT NULL auto_increment,
	description VARCHAR(50) NOT NULL,
	type VARCHAR(30) NULL,
	id_employee BIGINT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (id_employee) REFERENCES employee(id)
)engine=InnoDB default charset=utf8;

