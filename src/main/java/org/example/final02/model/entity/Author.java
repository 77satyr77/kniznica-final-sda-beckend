package org.example.final02.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAuthor;

    private String firstNameAuthor;
    private String lastNameAuthor;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
