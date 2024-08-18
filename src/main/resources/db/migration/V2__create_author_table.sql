-- Vytvorenie tabulky "authors"
CREATE TABLE authors (
                        id_author BIGINT AUTO_INCREMENT PRIMARY KEY,
                        first_name_author VARCHAR(255) NOT NULL,
                        last_name_author VARCHAR(255) NOT NULL
);
