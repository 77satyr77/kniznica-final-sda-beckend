package org.example.final02.controller;

import lombok.RequiredArgsConstructor;
import org.example.final02.model.entity.Book;
import org.example.final02.model.entity.dto.BookDto;
import org.example.final02.model.entity.record.BookRecord;
import org.example.final02.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {
    private final BookService bookService;

    @PostMapping("/createBook")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto){
        Book createBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(createBook, HttpStatus.CREATED);
    }

    @PostMapping("/createBookRecord")
    public ResponseEntity<Book> addBook(@RequestBody BookRecord bookRecord){
        Book createBook = bookService.createBook(bookRecord);
        return new ResponseEntity<>(createBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(@RequestParam Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam Long bookId){
        bookService.changeStatus(bookId);
        return new ResponseEntity<>("Status changed", HttpStatus.OK);
    }



}
