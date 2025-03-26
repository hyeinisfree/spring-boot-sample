package com.example.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
public class Book {
    @Setter
    private Long id = 1L;
    private Author author;
    private String title;
    private String subtitle;
    private Genre genre;
    private Boolean isSeries;
    private LocalDate publishedDate;

    public void updateBook(Author author, String title, String subtitle, Genre genre, Boolean isSeries, LocalDate publishedDate) {
        this.author = author;
        this.title = title;
        this.subtitle = subtitle;
        this.genre = genre;
        this.isSeries = isSeries;
        this.publishedDate = publishedDate;
    }
}
