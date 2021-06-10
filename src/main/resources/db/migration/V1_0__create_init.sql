CREATE TABLE user (
   id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(80) NOT NULL,
   password VARCHAR(80) NOT NULL,
   login VARCHAR(80) NOT NULL,
   created_date DATE NOT NULL,
   updated_date DATE NULL,
   email VARCHAR(100) NOT NULL,
   admin INT NOT NULL,   
   PRIMARY KEY (id) 
);