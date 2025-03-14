package com.example.demo.controller.dto;

import com.example.demo.service.dto.AuthorServiceDto;

public class AuthorRequestDto {

    private String name;
    private String nationality;
    private Integer age;

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public Integer getAge() {
        return age;
    }

    public AuthorServiceDto toServiceDto() {
        return new AuthorServiceDto(name, nationality, age);
    }
}
