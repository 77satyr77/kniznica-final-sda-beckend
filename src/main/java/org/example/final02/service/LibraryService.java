package org.example.final02.service;

import org.example.final02.model.entity.Author;
import org.example.final02.model.entity.Book;
import org.example.final02.model.entity.dto.AuthorDTO;
import org.example.final02.model.entity.dto.BookDTO;
import org.springframework.stereotype.Service;

@Service
public interface LibraryService {

    /**
     * Creates a new Book entity based on the provided BookDTO.
     * This method will associate the book with an existing author
     * identified by the authorId in the BookDTO.
     *
     * @param bookDTO The DTO containing details for the book to be created.
     * @return The created Book entity.
     * @throws IllegalArgumentException if the author associated with the book is not found.
     */
    Book createBook(BookDTO bookDTO);

    /**
     * Deletes the Book entity identified by the given bookId.
     * If the book is not found, an exception will be thrown.
     *
     * @param bookId The ID of the book to be deleted.
     * @throws IllegalArgumentException if the book with the given ID is not found.
     */
    void deleteBook(Long bookId);

    /**
     * Creates a new Author entity based on the provided AuthorDTO.
     * The books associated with the author can be managed separately.
     *
     * @param authorDTO The DTO containing details for the author to be created.
     * @return The created Author entity.
     */
    Author createAuthor(AuthorDTO authorDTO);

    /**
     * Deletes the Author entity identified by the given authorId.
     * If the author is not found, an exception will be thrown.
     *
     * @param authorId The ID of the author to be deleted.
     * @throws IllegalArgumentException if the author with the given ID is not found.
     */
    void deleteAuthor(Long authorId);
}
