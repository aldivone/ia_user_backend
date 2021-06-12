CREATE TABLE user (
   id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(80) NOT NULL,
   password VARCHAR(80) NOT NULL,
   login VARCHAR(80) NOT NULL,
   created_date TIMESTAMP NOT NULL,
   updated_date TIMESTAMP NULL,
   email VARCHAR(100) NOT NULL,
   admin INT NOT NULL,   
   PRIMARY KEY (id) 
);

INSERT INTO user (id, name, password, login, created_date, email, admin) VALUES (100000000, 'Administrador', 'qazxsw123', 'admin', current_date, 'admin@gmail.com', 1);