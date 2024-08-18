package org.example.final02.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idBook;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors authors;

    private boolean isAvailable;
}
