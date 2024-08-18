package org.example.final02.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBook;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private boolean isAvailable;
}
