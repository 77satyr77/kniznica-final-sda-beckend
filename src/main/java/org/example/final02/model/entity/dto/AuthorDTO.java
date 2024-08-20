package org.example.final02.model.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {
    private long authorId;
    private String firstName;
    private String lastName;
    private List<Long> bookIds;
}
