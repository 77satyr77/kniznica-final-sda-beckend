# V1__create_users_table.sql

CREATE TABLE my_user (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         role ENUM('USER', 'ADMIN' , 'SUPER_ADMIN') NOT NULL DEFAULT 'USER'

);
