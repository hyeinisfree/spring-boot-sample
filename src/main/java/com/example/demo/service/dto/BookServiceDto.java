package com.example.demo.service.dto;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class BookServiceDto {

    private final Long authorId;
    private final String title;
    private final String subtitle;
    private final Genre genre;
    private final Boolean isSeries;
    private final LocalDate publishedDate;

    public Book toBook(Author author) {
        return Book.builder()
                .author(author)
                .title(title)
                .subtitle(subtitle)
                .genre(genre)
                .isSeries(isSeries)
                .publishedDate(publishedDate)
                .build();
    }

}
