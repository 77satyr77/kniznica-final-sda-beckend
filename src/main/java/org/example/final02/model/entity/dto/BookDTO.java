package org.example.final02.model.entity.dto;

import lombok.Data;

@Data
public class BookDTO {
    private long bookId;
    private String title;
    private boolean available;
    private long authorId;
    private String authorFirstName;
    private String authorLastName;
}
