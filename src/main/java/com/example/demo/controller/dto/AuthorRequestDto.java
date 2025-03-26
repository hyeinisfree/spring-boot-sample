package com.example.demo.controller.dto;

import com.example.demo.service.dto.AuthorServiceDto;
import lombok.Getter;

@Getter
public class AuthorRequestDto {

    private String name;
    private String nationality;
    private Integer age;

    public AuthorServiceDto toServiceDto() {
        return AuthorServiceDto.builder()
                .name(name)
                .nationality(nationality)
                .age(age)
                .build();
    }
}
