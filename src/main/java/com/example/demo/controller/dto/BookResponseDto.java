package com.example.demo.controller.dto;

import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class BookResponseDto {
    private final Long id;
    private final AuthorResponseDto author;
    private final String title;
    private final String subtitle;
    private final Genre genre;
    private final Boolean isSeries;
    private final LocalDate publishedDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime updatedAt;

    public static BookResponseDto of(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .author(AuthorResponseDto.of(book.getAuthor()))
                .title(book.getTitle())
                .subtitle(book.getSubtitle())
                .genre(book.getGenre())
                .isSeries(book.getIsSeries())
                .publishedDate(book.getPublishedDate())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }
}
