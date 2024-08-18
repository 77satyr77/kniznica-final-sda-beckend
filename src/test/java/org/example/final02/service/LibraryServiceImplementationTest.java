package org.example.final02.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.example.final02.model.entity.Author;
import org.example.final02.model.entity.Book;
import org.example.final02.model.entity.dto.AuthorDTO;
import org.example.final02.model.entity.dto.BookDTO;
import org.example.final02.repository.AuthorRepository;
import org.example.final02.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class LibraryServiceImplementationTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private LibraryServiceImplementation libraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBookSuccess() {
        // Arrange
        Author author = new Author(1L, "John", "Doe", null);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(1L);
        bookDTO.setTitle("Test Book");
        bookDTO.setAuthorId(1L);
        bookDTO.setAvailable(true);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Book createdBook = libraryService.createBook(bookDTO);

        // Assert
        assertNotNull(createdBook);
        assertEquals("Test Book", createdBook.getTitle());
        assertEquals(author, createdBook.getAuthor());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testCreateBookAuthorNotFound() {
        // Arrange
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(1L);
        bookDTO.setTitle("Test Book");
        bookDTO.setAuthorId(99L); // Invalid author ID

        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> libraryService.createBook(bookDTO));
        assertEquals("Author not found with ID: 99", exception.getMessage());
    }

    @Test
    void testDeleteBookSuccess() {
        // Arrange
        when(bookRepository.existsById(1L)).thenReturn(true);

        // Act
        libraryService.deleteBook(1L);

        // Assert
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBookNotFound() {
        // Arrange
        when(bookRepository.existsById(99L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> libraryService.deleteBook(99L));
        assertEquals("Book not found with ID: 99", exception.getMessage());
    }

    @Test
    void testCreateAuthorSuccess() {
        // Arrange
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorId(1L);
        authorDTO.setFirstName("John");
        authorDTO.setLastName("Doe");

        when(authorRepository.save(any(Author.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Author createdAuthor = libraryService.createAuthor(authorDTO);

        // Assert
        assertNotNull(createdAuthor);
        assertEquals("John", createdAuthor.getFirstNameAuthor());
        assertEquals("Doe", createdAuthor.getLastNameAuthor());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testDeleteAuthorSuccess() {
        // Arrange
        when(authorRepository.existsById(1L)).thenReturn(true);

        // Act
        libraryService.deleteAuthor(1L);

        // Assert
        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAuthorNotFound() {
        // Arrange
        when(authorRepository.existsById(99L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> libraryService.deleteAuthor(99L));
        assertEquals("Author not found with ID: 99", exception.getMessage());
    }
}
