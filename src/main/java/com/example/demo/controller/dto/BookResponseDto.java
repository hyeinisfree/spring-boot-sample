package com.example.demo.controller.dto;

import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class BookResponseDto {
    private final Long id;
    private final String author;
    private final String title;
    private final String subtitle;
    private final Genre genre;
    private final Boolean isSeries;
    private final LocalDate publishedDate;

    public static BookResponseDto of(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .author(book.getAuthor().getName())
                .title(book.getTitle())
                .subtitle(book.getSubtitle())
                .genre(book.getGenre())
                .isSeries(book.getIsSeries())
                .publishedDate(book.getPublishedDate())
                .build();
    }
}
