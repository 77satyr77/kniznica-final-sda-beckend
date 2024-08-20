package org.example.final02.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.final02.model.entity.Author;
import org.example.final02.model.entity.Book;
import org.example.final02.model.entity.dto.AuthorDTO;
import org.example.final02.model.entity.dto.BookDTO;
import org.example.final02.model.entity.dto.MyCustomMapper;
import org.example.final02.repository.AuthorRepository;
import org.example.final02.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryServiceImplementation implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Book createBook(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with ID: " + bookDTO.getAuthorId()));

        Book book = MyCustomMapper.toBook(bookDTO, author);

        Book savedBook = bookRepository.save(book);
        log.info("Book created: {}", MyCustomMapper.toBookDTO(savedBook));

        return savedBook;
    }

    @Override
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("Book not found with ID: " + bookId);
        }

        bookRepository.deleteById(bookId);
        log.info("Book deleted with ID: {}", bookId);
    }

    @Override
    public Author createAuthor(AuthorDTO authorDTO) {
        Author author = MyCustomMapper.toAuthor(authorDTO);

        Author savedAuthor = authorRepository.save(author);
        log.info("Author created: {}", MyCustomMapper.toAuthorDTO(savedAuthor));

        return savedAuthor;
    }

    @Override
    public void deleteAuthor(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new IllegalArgumentException("Author not found with ID: " + authorId);
        }

        authorRepository.deleteById(authorId);
        log.info("Author deleted with ID: {}", authorId);
    }
}