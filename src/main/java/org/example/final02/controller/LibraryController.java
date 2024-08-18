package org.example.final02.controller;

import lombok.RequiredArgsConstructor;
import org.example.final02.model.entity.Author;
import org.example.final02.model.entity.Book;
import org.example.final02.model.entity.dto.AuthorDTO;
import org.example.final02.model.entity.dto.BookDTO;
import org.example.final02.model.entity.dto.MyCustomMapper;
import org.example.final02.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    /**
     * Endpoint to create a new book.
     *
     * @param bookDTO The details of the book to be created.
     * @return ResponseEntity containing the created Book entity.
     */
    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book createdBook = libraryService.createBook(bookDTO);
        return new ResponseEntity<>(MyCustomMapper.toBookDTO(createdBook), HttpStatus.CREATED);
    }

    /**
     * Endpoint to delete a book by ID.
     *
     * @param bookId The ID of the book to be deleted.
     * @return ResponseEntity with no content status.
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long bookId) {
        libraryService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint to create a new author.
     *
     * @param authorDTO The details of the author to be created.
     * @return ResponseEntity containing the created Author entity.
     */
    @PostMapping("/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author createdAuthor = libraryService.createAuthor(authorDTO);
        return new ResponseEntity<>(MyCustomMapper.toAuthorDTO(createdAuthor), HttpStatus.CREATED);
    }

    /**
     * Endpoint to delete an author by ID.
     *
     * @param authorId The ID of the author to be deleted.
     * @return ResponseEntity with no content status.
     */
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long authorId) {
        libraryService.deleteAuthor(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
