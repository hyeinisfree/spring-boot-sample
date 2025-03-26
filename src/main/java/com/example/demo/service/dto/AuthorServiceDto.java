package com.example.demo.service.dto;

import com.example.demo.domain.Author;
import lombok.Builder;

@Builder
public class AuthorServiceDto {
    private String name;
    private String nationality;
    private Integer age;

    public Author toAuthor() {
        return Author.builder()
                .name(name)
                .nationality(nationality)
                .age(age)
                .build();
    }
}
