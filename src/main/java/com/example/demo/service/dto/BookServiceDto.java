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

    private  Long authorId;
    private String title;
    private String subtitle;
    private Genre genre;
    private Boolean isSeries;
    private LocalDate publishedDate;

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
