-- Creating the table: customer_info
CREATE TABLE customer_info
(
	customer_id VARCHAR(10) NOT NULL,
	first_name VARCHAR(100), 
	last_name VARCHAR(100),
	age INT,
    PRIMARY KEY ( customer_id )
);

