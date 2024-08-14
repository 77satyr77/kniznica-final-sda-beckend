package org.example.final02.service;

import org.example.final02.model.entity.Book;
import org.example.final02.model.entity.dto.BookDto;
import org.example.final02.model.entity.record.BookRecord;

public interface BookService {
//    List<Book> findAllBooks(Long bookId);
    Book createBook(BookDto bookDto);
    Book createBook(BookRecord bookRecord);

    void deleteBook(Long bookId);

    Book changeStatus(Long bookId);


}
