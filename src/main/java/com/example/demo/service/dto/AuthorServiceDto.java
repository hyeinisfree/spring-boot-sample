package com.example.demo.service.dto;

import com.example.demo.domain.Author;

public class AuthorServiceDto {
    private String name;
    private String nationality;
    private Integer age;

    public AuthorServiceDto(String name, String nationality, Integer age) {
        this.name = name;
        this.nationality = nationality;
        this.age = age;
    }

    public Author toAuthor() {
        return new Author(name, nationality, age);
    }
}
