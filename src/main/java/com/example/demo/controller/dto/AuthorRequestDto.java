package com.example.demo.controller.dto;

import com.example.demo.service.dto.AuthorServiceDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class AuthorRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "국적은 필수입니다.")
    private String nationality;

    @Positive(message = "나이는 양수여야 합니다.")
    private Integer age;

    public AuthorServiceDto toServiceDto() {
        return AuthorServiceDto.builder()
                .name(name)
                .nationality(nationality)
                .age(age)
                .build();
    }
}
