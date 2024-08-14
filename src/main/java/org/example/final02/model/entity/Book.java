package org.example.final02.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String name;
    private String author;
    private Long ISBN;
    private Boolean isFree;

//    public void setIsFree(Booolean);

    public void changeStatus(){
        this.isFree = !isFree;
    }
    public Book(String name, String author, Long ISBN, Boolean isFree){
        this.name = name;
        this.author = author;
        this.ISBN = ISBN;
        this.isFree = isFree;

    }
}
