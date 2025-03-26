package com.example.demo.service.dto;

import com.example.demo.domain.Author;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthorServiceDto {
    private final String name;
    private final String nationality;
    private final Integer age;

    public Author toAuthor() {
        return Author.builder()
                .name(name)
                .nationality(nationality)
                .age(age)
                .build();
    }
}
