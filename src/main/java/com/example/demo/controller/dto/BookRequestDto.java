package com.example.demo.controller.dto;

import com.example.demo.domain.Genre;
import com.example.demo.service.dto.BookServiceDto;

import java.time.LocalDate;

public class BookRequestDto {
    private  Long authorId;
    private String title;
    private String subtitle;
    private Genre genre;
    private Boolean isSeries;
    private LocalDate publishedDate;

    public Long getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Genre getGenre() {
        return genre;
    }

    public Boolean getIsSeries() {
        return isSeries;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public BookServiceDto toServiceDto() {
        return new BookServiceDto(authorId, title, subtitle, genre, isSeries, publishedDate);
    }
}
