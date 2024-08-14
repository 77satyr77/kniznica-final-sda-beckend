package org.example.final02.model.entity.record;

public record BookRecord(
        String name,
        String author,
        Long ISBN,
        Boolean isFree) {
}
