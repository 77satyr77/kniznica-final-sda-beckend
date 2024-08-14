package org.example.final02.service;

import lombok.RequiredArgsConstructor;
import org.example.final02.model.entity.Book;
import org.example.final02.model.entity.dto.BookDto;
import org.example.final02.model.entity.record.BookRecord;
import org.example.final02.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;
    @Override
    public Book createBook(BookDto bookDto) {
        Book book = new Book(
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getISBN(),
                bookDto.getIsFree());

        return bookRepository.save(book);
    }

    @Override
    public Book createBook(BookRecord bookRecord) {
        Book book = new Book(
                bookRecord.name(),
                bookRecord.author(),
                bookRecord.ISBN(),
                bookRecord.isFree());

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book changeStatus(Long bookId) {
        Book book = bookRepository.getReferenceById(bookId);
        book.changeStatus();
        return bookRepository.save(book);
    }
}
