-- Vytvorenie tabulky "books"

CREATE TABLE books (
    id_book BIGINT AUTO_INCREMENT PRIMARY KEY ,
    title VARCHAR(255) NOT NULL ,
    author_id BIGINT NOT NULL ,
    is_available BOOLEAN NOT NULL ,
    CONSTRAINT fk_author FOREIGN KEY (author_id) references authors(id_author)

)