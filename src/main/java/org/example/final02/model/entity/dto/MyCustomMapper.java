package org.example.final02.model.entity.dto;

import org.example.final02.model.entity.Author;
import org.example.final02.model.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyCustomMapper {

    public static BookDTO toBookDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(book.getIdBook());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAvailable(book.isAvailable());

        if (book.getAuthor() != null) {
            bookDTO.setAuthorId(book.getAuthor().getIdAuthor());
            bookDTO.setAuthorFirstName(book.getAuthor().getFirstNameAuthor());
            bookDTO.setAuthorLastName(book.getAuthor().getLastNameAuthor());
        }

        return bookDTO;
    }

    public static Book toBook(BookDTO bookDTO, Author author) {
        if (bookDTO == null) {
            return null;
        }

        Book book = new Book();
        book.setIdBook(bookDTO.getBookId());
        book.setTitle(bookDTO.getTitle());
        book.setAvailable(bookDTO.isAvailable());
        book.setAuthor(author);

        return book;
    }

    public static AuthorDTO toAuthorDTO(Author author) {
        if (author == null) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorId(author.getIdAuthor());
        authorDTO.setFirstName(author.getFirstNameAuthor());
        authorDTO.setLastName(author.getLastNameAuthor());

        if (author.getBooks() != null) {
            List<Long> bookIds = author.getBooks().stream()
                    .map(Book::getIdBook)
                    .collect(Collectors.toList());
            authorDTO.setBookIds(bookIds);
        }

        return authorDTO;
    }

    public static Author toAuthor(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            return null;
        }

        Author author = new Author();
        author.setIdAuthor(authorDTO.getAuthorId());
        author.setFirstNameAuthor(authorDTO.getFirstName());
        author.setLastNameAuthor(authorDTO.getLastName());

        return author;
    }

    public static List<BookDTO> toBookDTOList(List<Book> books) {
        if (books == null) {
            return null;
        }

        return books.stream()
                .map(MyCustomMapper::toBookDTO)
                .collect(Collectors.toList());
    }

    public static List<AuthorDTO> toAuthorDTOList(List<Author> authors) {
        if (authors == null) {
            return null;
        }

        return authors.stream()
                .map(MyCustomMapper::toAuthorDTO)
                .collect(Collectors.toList());
    }
}
