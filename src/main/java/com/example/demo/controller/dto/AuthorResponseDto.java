package com.example.demo.controller.dto;

import com.example.demo.domain.Author;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthorResponseDto {
    private final Long id;
    private final String name;
    private final String nationality;
    private final Integer age;

    public static AuthorResponseDto of(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .nationality(author.getNationality())
                .age(author.getAge())
                .build();
    }
}
