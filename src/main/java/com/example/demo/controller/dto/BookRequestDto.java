package com.example.demo.controller.dto;

import com.example.demo.domain.Genre;
import com.example.demo.service.dto.BookServiceDto;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookRequestDto {
    private Long authorId;
    private String title;
    private String subtitle;
    private Genre genre;
    private Boolean isSeries;
    private LocalDate publishedDate;

    public BookServiceDto toServiceDto() {
        return BookServiceDto.builder()
                .authorId(authorId)
                .title(title)
                .subtitle(subtitle)
                .genre(genre)
                .isSeries(isSeries)
                .publishedDate(publishedDate)
                .build();
    }
}
