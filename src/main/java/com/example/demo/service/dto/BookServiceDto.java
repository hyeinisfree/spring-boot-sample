package com.example.demo.service.dto;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Genre;

import java.time.LocalDate;

public class BookServiceDto {

    private  Long authorId;
    private String title;
    private String subtitle;
    private Genre genre;
    private Boolean isSeries;
    private LocalDate publishedDate;

    public BookServiceDto(Long authorId, String title, String subtitle, Genre genre, Boolean isSeries, LocalDate publishedDate) {
        this.authorId = authorId;
        this.title = title;
        this.subtitle = subtitle;
        this.genre = genre;
        this.isSeries = isSeries;
        this.publishedDate = publishedDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean getIsSeries() {
        return isSeries;
    }

    public void setIsSeries(Boolean series) {
        isSeries = series;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Book toBook(Author author) {
        return new Book(author, title, subtitle, genre, isSeries, publishedDate);
    }

}
