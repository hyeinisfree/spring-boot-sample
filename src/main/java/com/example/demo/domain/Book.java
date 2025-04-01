package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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
