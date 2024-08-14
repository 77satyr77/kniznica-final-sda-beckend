package org.example.final02.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookDto {
    private String name;
    private String author;
    private Long ISBN;
    private Boolean isFree;

}
